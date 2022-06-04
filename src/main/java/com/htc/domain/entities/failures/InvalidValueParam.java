package com.htc.domain.entities.failures;

/**
 * Некорректное значение параметра.
 */
public enum InvalidValueParam implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(-1, "Некорректное значение переданного параметра."),

  INVALID_ENTITY_ID(-2, "Некорректный идентификатор."),

  INVALID_USER_NAME(-3, "Некорректное имя."),
  INVALID_USER_PASSWORD(-4, "Некорректный пароль."),
  INVALID_USER_EMAIL(-5, "Некорректная электронная почта."),
  INVALID_USER_IMAGE(-6, "Некорректное изображение.");

  private final Integer status;
  private final String message;

  InvalidValueParam(Integer status, String message) {
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
