package com.htc.application.dtos.exceptions;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import java.util.Map;
import lombok.Getter;

/**
 * Ответ. Представление ошибки {@link com.htc.domain.entities.failures.InvalidValues InvalidValues}.
 */
public class InvalidValuesResponse extends CustomResponseStatusException {
  /**
   * Список ошибок некорректных полей.
   *
   * @return values список ошибок некорректных полей
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Iterable<InvalidValueParamResponse> problems;

  /**
   * Создание представления ошибки.
   *
   * @param failure сущность ошибки, подробнее {@link Failure}
   */
  public InvalidValuesResponse(Failure failure, Map<InvalidValueParam, String> values) {
    super(failure);
    this.problems = values.entrySet().stream()
            .map(entry -> new InvalidValueParamResponse(entry.getKey(), entry.getValue())).toList();
  }
}