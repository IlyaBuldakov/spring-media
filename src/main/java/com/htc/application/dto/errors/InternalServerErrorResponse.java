package com.htc.application.dto.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление внутренней ошибки сервера.
 */
public class InternalServerErrorResponse extends AbstractDtoError {

  /**
   * HTTP статус ошибки.
   */
  private final @Getter HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

  /**
   * Код HTTP статуса для сериализации ошибки.
   */
  private final @Getter int statusCode = httpStatus.value();

  /**
   * Стандартное сообщение ошибки.
   */
  private final static String DEFAULT_MESSAGE = "Внутренняя ошибка сервера";

  public InternalServerErrorResponse() {
    super(DEFAULT_MESSAGE);
  }
}
