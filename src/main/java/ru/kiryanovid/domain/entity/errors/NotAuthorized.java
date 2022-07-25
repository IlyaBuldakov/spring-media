package ru.kiryanovid.domain.entity.errors;

/**
 * Ошибка авторизации.
 */
public enum NotAuthorized implements Failure {
  /**
  * Сообщение по умолчанию.
  */
  DEFAULT_MESSAGE("Недостаточно прав для выполнения операции");
  private final String message;

  NotAuthorized(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
