package com.htc.domain.entities.failures;

import java.util.HashMap;
import java.util.Map;

/**
 * Некорректные значения.
 */
public record InvalidValues(Map<InvalidValue, String> invalidValues) implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  private static final String DEFAULT_MESSAGE =
      "Запрос пользователя содержит некорректные значения.";

  @Override
  public String getMessage() {
    return DEFAULT_MESSAGE;
  }

  public InvalidValues() {
    this(new HashMap<>());
  }
}
