package com.htc.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.htc.application.dto.authentication.LoginResponse;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с Access- и Refresh- токенами.
 */
@Service
public class TokenService {
  private final Pattern tokenRegex = Pattern.compile(
          "Bearer (?<token>[^\\s.]+.[^\\s.]+.[^\\s.]+)");

  private final Algorithm algorithm;

  /**
   * Срок действия Access-токена (10 минут).
   */
  private static final int ACCESS_TOKEN_EXPIRATION_SECONDS = 10 * 60;

  /**
   * Срок действия REFRESH-токена (1 сутки).
   */
  private static final int REFRESH_TOKEN_EXPIRATION_SECONDS = 24 * 60 * 60;

  public Either<Failure, LoginResponse> createTokens(Id id, User.Role role) {
    var accessToken = createToken(id, role, ACCESS_TOKEN_EXPIRATION_SECONDS);
    var refreshToken = createToken(id, role, REFRESH_TOKEN_EXPIRATION_SECONDS);
    return Either.right(new LoginResponse(accessToken, refreshToken));
  }

  public String getToken(String authorizationHeader) {
    var matcher = tokenRegex.matcher(authorizationHeader);
    matcher.find();

    return matcher.group("token");
  }

  public boolean isTokenValid(String token) {
    try {
      var verifier = JWT.require(algorithm)
              .acceptLeeway(60)
              .build();
      var decodedToken = verifier.verify(token);
    } catch (JWTVerificationException exception) {
      return false;
    }

    return true;
  }

  public DecodedJWT parseToken(String token) {
    return JWT.require(algorithm)
            .build()
            .verify(token);
  }

  private String createToken(Id id, User.Role role, int expiresAt) {
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
}