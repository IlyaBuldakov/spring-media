package com.htc.application.dto.authentication;

import lombok.AllArgsConstructor;

/**
 * Представление Access- и Refresh-токенов (ответ на запрос).
 */
@AllArgsConstructor
public class LoginResponse {
  public String accessToken;
  public String refreshToken;
}
