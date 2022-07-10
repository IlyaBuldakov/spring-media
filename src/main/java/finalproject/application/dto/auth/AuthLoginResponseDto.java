package finalproject.application.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ответ сервиса авторизации.
 */
@AllArgsConstructor
public class AuthLoginResponseDto {

  @Getter
  private final String type = "Bearer";
  /**
   * Возвращает @return токен доступа.
   */
  private @Getter String accessToken;

  /**
   * Возвращает @return токен обновления.
   */
  private @Getter String refreshToken;

}

