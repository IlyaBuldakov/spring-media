package domain.entities.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Токен обновления
 */
@AllArgsConstructor
public class AuthRefreshTokenDto {

    /**
     * @return Токен обновления
     */
    private @Getter String refreshToken;

}
