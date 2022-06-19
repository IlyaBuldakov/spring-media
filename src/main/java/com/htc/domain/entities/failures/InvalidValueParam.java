package com.htc.domain.entities.failures;

import org.springframework.http.HttpStatus;

/**
 * Некорректное значение параметра.
 */
public enum InvalidValueParam implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(
          HttpStatus.FORBIDDEN, "Некорректное значение переданного параметра.", "parameter"
  ),

  INVALID_ENTITY_ID(HttpStatus.FORBIDDEN, "Некорректный идентификатор.", "id"),

  INVALID_USER_NAME(HttpStatus.FORBIDDEN, "Некорректное имя.", "name"),
  INVALID_USER_PASSWORD(HttpStatus.FORBIDDEN, "Некорректный пароль.", "password"),
  INVALID_USER_EMAIL(HttpStatus.FORBIDDEN, "Некорректная электронная почта.", "email"),
  INVALID_USER_IMAGE(HttpStatus.FORBIDDEN, "Некорректное изображение.", "avatar");

  private final HttpStatus status;
  private final String message;
  private final String field;

  InvalidValueParam(HttpStatus status, String message, String field) {
    this.status = status;
    this.message = message;
    this.field = field;
  }

  @Override
  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }

  /**
   * Получить наименование поля, в котором возникла ошибка.
   *
   * @return field наименование поля
   */
  public String getField() {
    return field;
  }
}
