package com.htc.domain.entities.failures;

/**
 * Внутренняя ошибка репозитория.
 */
public enum RepositoryFailure implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE("Внутренняя ошибка репозитория.");

  RepositoryFailure(String message) {
    this.message = message;
  }

  private final String message;

  @Override
  public String getMessage() {
    return message;
  }
}
