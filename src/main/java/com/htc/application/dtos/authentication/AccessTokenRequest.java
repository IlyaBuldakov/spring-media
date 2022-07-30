package com.htc.application.dtos.authentication;

import lombok.Getter;
import lombok.Setter;

/**
 * Запрос. Представление на обновление Access-токена.
 */
@Getter
@Setter
public class AccessTokenRequest {
  private String refreshToken;
}
