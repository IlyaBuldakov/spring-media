package ru.kiryanovid.domain.entity.errors;

/**
 * Внутренняя ошибка репозитория.
 */
public enum RepositoryFailure implements Failure {
  /**
  * Сообщение по умолчанию.
  */
  DEFAULT_MESSAGE("Внутренняя ошибка репозитория.");

  private final String message;

  RepositoryFailure(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
