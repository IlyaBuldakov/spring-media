package com.htc.domain.entities.failures;

import java.util.List;
import org.springframework.http.HttpStatus;

/**
 * Некорректные значения.
 */
public record InvalidValues(List<InvalidValueParam> values) implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  private static final String DEFAULT_MESSAGE = "Некорректные значения.";

  private static final HttpStatus status = HttpStatus.BAD_REQUEST;

  @Override
  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return DEFAULT_MESSAGE;
  }
}
