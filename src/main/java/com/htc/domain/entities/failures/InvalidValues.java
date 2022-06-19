package com.htc.domain.entities.failures;

import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Некорректные значения.
 */
public class InvalidValues implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  private static final String DEFAULT_MESSAGE = "Некорректные значения.";

  private static final HttpStatus status = HttpStatus.BAD_REQUEST;

  /**
   * Список ошибок некорректных полей.
   */
  private final @Getter List<InvalidValueParam> values;

  /**
   * Создание сборной ошибки.
   *
   * @param values список ошибок
   */
  public InvalidValues(List<InvalidValueParam> values) {
    this.values = values;
  }

  @Override
  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return DEFAULT_MESSAGE;
  }
}
