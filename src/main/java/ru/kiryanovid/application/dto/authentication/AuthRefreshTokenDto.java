package ru.kiryanovid.application.dto.authentication;

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
