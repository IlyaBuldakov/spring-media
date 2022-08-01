package com.htc.application.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.dto.errors.UnauthorizedResponse;
import com.htc.application.dto.login.LoginResponse;
import com.htc.application.security.UserAuthentication;
import com.htc.application.services.JwtService;
import com.htc.domain.entities.failure.Unauthorized;
import com.htc.domain.entities.user.Role;
import java.util.Date;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Реализация {@link JwtService}.
 */
@Service
public class JwtServiceImpl implements JwtService {

  public JwtServiceImpl(
          @Value("${authentication.key}") String key,
          @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
    this.algorithm = Algorithm.HMAC256(key);
  }

  /**
   * Алгоритм подписи JWT.
   */
  private final Algorithm algorithm;

  /**
   * Паттерн для получения токена из заголовка запроса.
   */
  private final Pattern tokenPattern
          = Pattern.compile("Bearer (?<token>[^\\s.]+.[^\\s.]+.[^\\s.]+)");

  private final UserDetailsService userDetailsService;

  /**
   * Время жизни Access-токена (10 минут).
   */
  private static final int ACCESS_TOKEN_LIFETIME_SECONDS = 10 * 60;

  /**
   * Время жизни Refresh-токена (30 дней).
   */
  private static final int REFRESH_TOKEN_LIFETIME_SECONDS = 30 * 24 * 60 * 60;

  /**
   * Создание JWT-токена.
   *
   * @param id       Идентификатор пользователя.
   * @param role     Роль пользователя.
   * @param email    Электронная почта пользователя.
   * @param lifeTime Время жизни токена.
   * @return JWT-токен.
   */
  @Override
  public String createJwtToken(int id, Role role, String email, int lifeTime) {
    var expiredTime = new Date();
    expiredTime.setTime(expiredTime.getTime() + (lifeTime * 1000L));
    return JWT.create()
            .withSubject(email)
            .withClaim("id", id)
            .withClaim("role", role.toString())
            .withExpiresAt(expiredTime)
            .sign(algorithm);
  }

  /**
   * Создание пары JWT-токенов.
   *
   * @param id    Идентификатор пользователя.
   * @param role  Роль пользователя.
   * @param email Электронная почта пользователя.
   * @return Пара JWT-токенов.
   */
  @Override
  public LoginResponse createPairOfTokens(int id, Role role, String email) {
    return new LoginResponse(
            createJwtToken(id, role, email, ACCESS_TOKEN_LIFETIME_SECONDS),
            createJwtToken(id, role, email, REFRESH_TOKEN_LIFETIME_SECONDS)
    );
  }

  /**
   * Получение токена из заголовка запроса.
   *
   * @param header Заголовок.
   * @return JWT-токен.
   */
  @Override
  public String getTokenFromHeader(String header) {
    try {
      var matcher = tokenPattern.matcher(header);
      matcher.find();
      return matcher.group("token");
    } catch (IllegalStateException e) {
      throw new UnauthorizedResponse(Unauthorized.DEFAULT_MESSAGE.getMessage());
    }
  }

  /**
   * Валидация токна.
   *
   * @param token JWT токен.
   * @return boolean.
   */
  public boolean isTokenValid(String token) {
    try {
      JWT.require(algorithm)
              .acceptLeeway(60)
              .build()
              .verify(token);
    } catch (JWTVerificationException exception) {
      return false;
    }
    return true;
  }

  /**
   * Получение электронной почты из токена.
   *
   * @param token Токен.
   * @return Электронная почта.
   */
  private String getEmailFromToken(String token) {
    return JWT.decode(token).getSubject();
  }

  /**
   * Получение идентификатора
   * пользователя из токена.
   *
   * @param token Токен.
   * @return Идентификатор пользователя.
   */
  private int getIdFromToken(String token) {
    return JWT.decode(token).getClaim("id").asInt();
  }

  /**
   * Получение реализации Authentication.
   *
   * @param token JWT токен.
   * @return Реализация Authentication.
   */
  @Override
  public Authentication getAuthentication(String token) {
    var email = getEmailFromToken(token);
    var id = getIdFromToken(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
    return new UserAuthentication(id, userDetails, "", userDetails.getAuthorities());
  }

  /**
   * Получение нового access-токна.
   *
   * @param refreshToken Refresh-токен.
   * @return Связка: новый access-токен и старый refresh-токен.
   */
  @Override
  public LoginResponse getNewAccessToken(String refreshToken) {
    DecodedJWT decodedJwt = JWT.decode(refreshToken);
    int id = decodedJwt.getClaim("id").asInt();
    Role role = Role.lookup(decodedJwt.getClaim("role").asString());
    String email = decodedJwt.getSubject();
    if (isTokenValid(refreshToken)) {
      if (role != null) {
        return new LoginResponse(
                createJwtToken(id, role, email, ACCESS_TOKEN_LIFETIME_SECONDS),
                refreshToken);
      }
    }
    throw new InternalServerErrorResponse();
  }
}
