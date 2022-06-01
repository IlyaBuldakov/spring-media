package ru.kiryanovid.domain.entity.authentication;

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
    @Getter private String accessToken;

    /**
     *  Токен обновления
     */
    @Getter private String refreshToken;
}
