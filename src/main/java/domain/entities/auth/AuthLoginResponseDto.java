package domain.entities.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ответ сервиса авторизации
 */
@AllArgsConstructor
public class AuthLoginResponseDto {

    /**
     * @return Токен доступа
     */
    private @Getter String accessToken;

    /**
     * @return Токен обновления
     */
    private @Getter String refreshToken;

}

