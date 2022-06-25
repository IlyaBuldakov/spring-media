package com.htc.domain.entities.failures;

/**
 * Некорректное значение параметра.
 */
public enum InvalidValueParam {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE("Некорректное значение переданного параметра."),

  INVALID_ENTITY_ID("Некорректный идентификатор."),

  INVALID_USER_NAME("Некорректное имя."),
  INVALID_USER_PASSWORD("Некорректный пароль."),
  INVALID_USER_EMAIL("Некорректная электронная почта."),
  INVALID_USER_IMAGE("Некорректное изображение.");

  private final String message;

  InvalidValueParam(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
