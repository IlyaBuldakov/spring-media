package com.htc.domain.entities.failures;

import org.springframework.http.HttpStatus;

/**
 * Некорректное значение параметра.
 */
public enum InvalidValueParam implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(HttpStatus.FORBIDDEN, "Некорректное значение переданного параметра."),

  INVALID_ENTITY_ID(HttpStatus.FORBIDDEN, "Некорректный идентификатор."),

  INVALID_USER_NAME(HttpStatus.FORBIDDEN, "Некорректное имя."),
  INVALID_USER_PASSWORD(HttpStatus.FORBIDDEN, "Некорректный пароль."),
  INVALID_USER_EMAIL(HttpStatus.FORBIDDEN, "Некорректная электронная почта."),
  INVALID_USER_IMAGE(HttpStatus.FORBIDDEN, "Некорректное изображение.");

  private final HttpStatus status;
  private final String message;

  InvalidValueParam(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override
  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
