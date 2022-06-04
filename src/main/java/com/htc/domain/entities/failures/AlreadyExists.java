package com.htc.domain.entities.failures;

/**
 * Сущность уже сушествует.
 */
public enum AlreadyExists implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(-1, "Сущность уже существует.");

  private final int status;
  private final String message;

  AlreadyExists(int status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override
  public int getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
