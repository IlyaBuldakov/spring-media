package com.htc.application.dto.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

/**
 * Общий класс представления ошибок.
 */
@JsonIgnoreProperties
        (value = {"cause", "stackTrace", "suppressed", "localizedMessage", "httpStatus"})
public abstract class AbstractDtoError extends RuntimeException {
  /**
   * Получение HTTP статуса ошибки.
   *
   * @return {@link HttpStatus HTTP статус}.
   */
  public abstract HttpStatus getHttpStatus();

  public AbstractDtoError(String message) {
    super(message);
  }
}