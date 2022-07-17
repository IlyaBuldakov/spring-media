package com.htc.domain.entities.failures;

/**
 * Сущность не найдена.
 */
public enum NotFound implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE("Сущность с указанным идентификатором не найдена."),

  // Пользователи.
  USER_WITH_EMAIL("Пользователь с указанной электронной почтой не найден.");

  NotFound(String message) {
    this.message = message;
  }

  private final String message;

  @Override
  public String getMessage() {
    return message;
  }
}
