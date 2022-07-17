package com.htc.domain.entities.failures;

/**
 * Сущность уже существует.
 */
public enum AlreadyExists implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE("Сущность уже существует."),

  // Пользователи
  USER_WITH_EMAIL("Пользователь с данным адресом электронной почты уже существует.");

  AlreadyExists(String message) {
    this.message = message;
  }

  private final String message;

  @Override
  public String getMessage() {
    return message;
  }
}
