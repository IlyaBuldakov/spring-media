package com.htc.application.dto.login;

import com.htc.application.dto.BaseResponse;
import lombok.Getter;

/**
 * Представление логина (запрос).
 */
public class LoginRequest implements BaseResponse {

  /**
   * E-mail пользователя.
   */
  private @Getter String email;

  /**
   * Пароль пользователя.
   */
  private @Getter String password;
}
