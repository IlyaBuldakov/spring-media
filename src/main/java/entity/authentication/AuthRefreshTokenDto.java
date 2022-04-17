package entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class AuthRefreshTokenDto {
    /**
     * Токен обновления
     */
    @Getter String refreshToken;
}
