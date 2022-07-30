package com.htc.application.dto.login;

import lombok.Getter;

/**
 * Представление логина (запрос).
 */
public class LoginRequest {

  /**
   * E-mail пользователя.
   */
  private @Getter String email;

  /**
   * Пароль пользователя.
   */
  private @Getter String password;
}
