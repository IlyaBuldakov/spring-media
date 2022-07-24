package ru.kiryanovid.application.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление запроса на вход в систему.
 */
@AllArgsConstructor
public class AuthLoginRequestDto {
  @Getter private String email;
  @Getter private String password;
}
