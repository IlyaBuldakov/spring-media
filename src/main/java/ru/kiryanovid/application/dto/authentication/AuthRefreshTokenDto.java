package ru.kiryanovid.application.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление запроса на обновление Access-токена.
 */
@AllArgsConstructor
public class AuthRefreshTokenDto {
  @Getter private String refreshToken;
}
