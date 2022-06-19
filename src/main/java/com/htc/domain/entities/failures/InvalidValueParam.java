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
          HttpStatus.BAD_REQUEST, "Некорректное значение переданного параметра.", "parameter"
  ),

  INVALID_ENTITY_ID(HttpStatus.BAD_REQUEST, "Некорректный идентификатор.", "id"),

  INVALID_USER_NAME(HttpStatus.BAD_REQUEST, "Некорректное имя.", "name"),
  INVALID_USER_PASSWORD(HttpStatus.BAD_REQUEST, "Некорректный пароль.", "password"),
  INVALID_USER_EMAIL(HttpStatus.BAD_REQUEST, "Некорректная электронная почта.", "email"),
  INVALID_USER_IMAGE(HttpStatus.BAD_REQUEST, "Некорректное изображение.", "avatar");

  private final HttpStatus status;
  private final String message;

  /**
   * Наименование поля, в котором возникла ошибка.
   */
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
