package ru.kiryanovid.application.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление Access- и Refresh-токенов (ответ на запрос).
 */
@AllArgsConstructor
public class AuthLoginResponseDto {
  @Getter private String accessToken;
  @Getter private String refreshToken;
}
