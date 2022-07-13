package com.htc.domain.entities.failures;

/**
 * Некорректное значение.
 */
public enum InvalidValue implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE("Некорректное значение переданного параметра."),

  // Сообщения общие для всех сущностей.
  /**
   * Сообщение о некорректном идентификаторе.
   */
  ENTITY_ID_IS_NOT_A_NUMBER("Идентификатор сущности должен быть числом."),
  /**
   * Сообщение о некорректном идентификаторе.
   */
  INVALID_ENTITY_ID("Идентификатор сущности не может быть меньше нуля."),

  // Сообщения для пользователей.
  /**
   * Сообщение о некорректном имени пользователя.
   */
  INVALID_USER_NAME("Некорректное имя пользователя."),
  /**
   * Сообщение о некорректной электронной почте пользователя.
   */
  INVALID_USER_EMAIL("Некорректная электронная почта пользователя."),
  INVALID_USER_PASSWORD("Некорректный пароль пользователя."),
  INVALID_USER_IMAGE("Некорректное изображения пользователя.");

  private final String message;

  @Override
  public String getMessage() {
    return message;
  }

  InvalidValue(String message) {
    this.message = message;
  }
}
