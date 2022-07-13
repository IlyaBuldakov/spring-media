package com.htc.domain.entities.failures;

/**
 * Недостаточно прав для выполнения операции.
 */
public enum Unauthorized implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE("Недостаточно прав для выполнения операции");

  Unauthorized(String message) {
    this.message = message;
  }

  private final String message;

  @Override
  public String getMessage() {
    return message;
  }
}
