package com.htc.application.dtos.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ответ. Представление Access- и Refresh-токенов.
 */
@Getter
@AllArgsConstructor
public class LoginResponse {
  private String accessToken;
  private String refreshToken;
}
