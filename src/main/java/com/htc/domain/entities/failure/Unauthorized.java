package com.htc.domain.entities.failure;

import lombok.Getter;

/**
 * Ошибка авторизации.
 */
public enum Unauthorized implements Failure {

  DEFAULT_MESSAGE("Вы не авторизованы"),

  BAD_CREDENTIALS("Неверный e-mail ли пароль");

  Unauthorized(String message) {
    this.message = message;
  }

  /**
   * Сообщение об ошибке.
   */
  private final @Getter String message;
}
