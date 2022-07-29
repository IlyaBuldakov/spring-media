package finalproject.application.services.auth;


import finalproject.application.dto.failures.NotAuthorizedDto;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.failures.Messages;
import finalproject.domain.entities.failures.NotAuthorized;
import finalproject.domain.entities.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Сервис обмена токенами аутентификации.
 */
@Component
public class JwtProvider {

  private final SecretKey jwtAccessSecret;
  private final SecretKey jwtRefreshSecret;

  public JwtProvider(
          @Value("${jwt.secret}") String jwtAccessSecret,
          @Value("${jwt.secret}") String jwtRefreshSecret
  ) {
    this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
    this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
  }

  /** Генерация Access-токена.
   *
   * @param user Пользователь
   * @return Sting access-токен
   */
  public String generateAccessToken(@NonNull User user) {
    final LocalDateTime now = LocalDateTime.now();
    final Instant accessExpirationInstant = now.plusMinutes(1000)
            .atZone(ZoneId.systemDefault())
            .toInstant();
    final Date accessExpiration = Date.from(accessExpirationInstant);
    return Jwts.builder()
            .setSubject(user.getEmail())
            .setExpiration(accessExpiration)
            .signWith(jwtAccessSecret)
            .claim("role", user.getRole())
            .claim("id", user.getId())
            .compact();
  }

  /** Генерация Refresh-токена.
   *
   * @param user Пользователь
   * @return Sting refresh-токен
   */
  public String generateRefreshToken(@NonNull User user) {
    final LocalDateTime now = LocalDateTime.now();
    final Instant refreshExpirationInstant = now.plusDays(30)
            .atZone(ZoneId.systemDefault())
            .toInstant();
    final Date refreshExpiration = Date.from(refreshExpirationInstant);
    return Jwts.builder()
            .setSubject(user.getEmail())
            .setExpiration(refreshExpiration)
            .signWith(jwtRefreshSecret)
            .compact();
  }

  public boolean validateAccessToken(@NonNull String accessToken) {
    return validateToken(accessToken, jwtAccessSecret);
  }

  public boolean validateRefreshToken(@NonNull String refreshToken) {
    return validateToken(refreshToken, jwtRefreshSecret);
  }

  private boolean validateToken(@NonNull String token, @NonNull Key secret) {
    try {
      Jwts.parserBuilder()
              .setSigningKey(secret)
              .build()
              .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      throw new NotAuthorizedDto(new NotAuthorized(Messages.AUTHORIZATION_FAILURE));
    }
  }

  public Claims getAccessClaims(@NonNull String token) {
    return getClaims(token, jwtAccessSecret);
  }

  public Claims getRefreshClaims(@NonNull String token) {
    return getClaims(token, jwtRefreshSecret);
  }

  private Claims getClaims(@NonNull String token, @NonNull Key secret) {
    return Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

}