package com.htc.application.dto.authentication;

/**
 * Представление Access- и Refresh-токенов (ответ на запрос).
 */
public class LoginResponse {
  public String accessToken;
  public String refreshToken;
}
