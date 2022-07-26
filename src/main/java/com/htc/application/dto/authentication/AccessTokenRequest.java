package com.htc.application.dto.authentication;

import lombok.AllArgsConstructor;

/**
 * Представление запроса на обновление Access-токена.
 */
@AllArgsConstructor
public class AccessTokenRequest {
  public String refreshToken;
}