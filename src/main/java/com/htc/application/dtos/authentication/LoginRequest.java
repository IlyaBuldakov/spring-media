package com.htc.application.dtos.authentication;

import lombok.Getter;
import lombok.Setter;

/**
 * Запрос. Представление на вход в систему.
 */
@Getter
@Setter
public class LoginRequest {
  private String email;
  private String password;
}
