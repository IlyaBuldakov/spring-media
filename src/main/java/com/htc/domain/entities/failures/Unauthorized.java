package com.htc.domain.entities.failures;

/**
 * Не авторизован.
 */
public enum Unauthorized implements Failure {
  /**
  * Сообщение по умолчанию.
  */
  DEFAULT_MESSAGE("Не авторизован.");

  Unauthorized(String message) {
    this.message = message;
  }

  private final String message;

  @Override
  public String getMessage() {
    return message;
  }
}