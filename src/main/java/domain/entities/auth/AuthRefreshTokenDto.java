package domain.entities.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Токен обновления.
 */
@AllArgsConstructor
public class AuthRefreshTokenDto {

  /**
   * Возвращает @return токен обновления.
   */
  private @Getter String refreshToken;

}
