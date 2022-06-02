package finalproject.application.dto.auth;

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
