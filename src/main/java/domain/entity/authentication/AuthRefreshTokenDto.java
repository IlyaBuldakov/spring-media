package domain.entity.authentication;

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
    @Getter private String refreshToken;
}
