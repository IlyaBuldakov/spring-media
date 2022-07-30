package com.htc.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.htc.application.dtos.authentication.LoginResponse;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.utility.parameters.Id;
import io.vavr.control.Either;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Сервис токена.
 */
@Service
public class TokenService {
  private final Pattern tokenRegex = Pattern.compile("Bearer (?<token>[^\\s.]+.[^\\s.]+.[^\\s.]+)");

  private final Algorithm algorithm;

  /**
   * Срок действия Access-токена.
   */
  private static final int ACCESS_TOKEN_EXPIRATION_SECONDS = 5 * 60;

  /**
   * Срок действия Refresh-токена.
   */
  private static final int REFRESH_TOKEN_EXPIRATION_SECONDS = 24 * 60 * 60;

  /**
   * Создание пары токенов.
   *
   * @param id идентификатор пользователя
   * @param role роль пользователя
   * @return tokens токены
   */
  public Either<Failure, LoginResponse> createTokens(Id id, Role role) {
    var accessToken = createToken(id, role, ACCESS_TOKEN_EXPIRATION_SECONDS);
    var refreshToken = createToken(id, role, REFRESH_TOKEN_EXPIRATION_SECONDS);
    return Either.right(new LoginResponse(accessToken, refreshToken));
  }

  /**
   * Получение токена.
   *
   * @param authorizationHeader заголовок
   * @return token токен
   */
  public String getToken(String authorizationHeader) {
    var matcher = tokenRegex.matcher(authorizationHeader);
    matcher.find();
    return matcher.group("token");
  }

  /**
   * Валидация токена.
   *
   * @param token токен
   * @return result результат
   */
  public boolean isTokenValid(String token) {
    try {
      var verifier = JWT.require(algorithm)
              .acceptLeeway(0)
              .build();
      var decodedToken = verifier.verify(token);
      // TODO: Добавить выброс иключения, если срок токен истек
      //  (com.auth0.jwt.exceptions.TokenExpiredException)
    } catch (JWTVerificationException exception) {
      return false;
    }
    return true;
  }

  /**
   * Разбор токена.
   *
   * @param token токен
   * @return result расшифрованный токен
   */
  public DecodedJWT parseToken(String token) {
    return JWT.require(algorithm)
            .build()
            .verify(token);
  }

  /**
   * Создание токена.
   *
   * @param id идентификатор пользователя
   * @param role роль пользователя
   * @param expiresAt срок действия до
   * @return token токен
   */
  private String createToken(Id id, Role role, int expiresAt) {
    var expirationDate = ZonedDateTime.now()
            .plusSeconds(expiresAt)
            .toInstant();

    return JWT.create()
            .withClaim("id", id.getValue())
            .withClaim("role", role.toString())
            .withExpiresAt(Date.from(expirationDate))
            .sign(algorithm);
  }

  public TokenService(@Value("${authentication.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Получение информации по аутентификации.
   *
   * @return info информация об аутентифицированном пользователе
   */
  // TODO: применить в контроллере
  public UserAuthenticationToken getAuthInfo() {
    return (UserAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
  }
}
