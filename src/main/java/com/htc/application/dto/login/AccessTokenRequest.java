package com.htc.application.dto.login;

import lombok.Getter;

/**
 * Представление запроса на получение
 * нового access-токена.
 */
public class AccessTokenRequest {

  private @Getter String refreshToken;
}
