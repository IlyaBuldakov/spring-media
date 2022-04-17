package entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class AuthLoginResponseDto {
    /**
     * Токен доступа
     */
    @Getter String accessToken;

    /**
     *  Токен обновления
     */
    @Getter String refreshToken;
}
