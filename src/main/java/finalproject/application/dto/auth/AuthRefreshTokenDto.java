package finalproject.application.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Токен обновления.
 */
@AllArgsConstructor
@NoArgsConstructor
public class AuthRefreshTokenDto {

  /**
   * Возвращает @return токен обновления.
   */
  private @Getter String refreshToken;

}
