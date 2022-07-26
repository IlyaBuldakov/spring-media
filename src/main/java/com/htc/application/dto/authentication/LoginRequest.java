package com.htc.application.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление запроса на вход в систему.
 */
@AllArgsConstructor
public class LoginRequest {
  public @Getter String email;
  public  @Getter String password;
}
