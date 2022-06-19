package com.htc.application.dtos.exceptions;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import java.util.List;
import lombok.Getter;

/**
 * Ответ. Представление ошибки {@link com.htc.domain.entities.failures.InvalidValues InvalidValues}.
 */
public class InvalidValuesResponse extends CustomResponseStatusException {
  /**
   * Список некорректных полей.
   *
   * @return values список некорректных полей
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter List<InvalidValueParamResponse> problems;

  /**
   * Создание представления ошибки.
   *
   * @param failure сущность ошибки, подробнее {@link Failure}
   */
  public InvalidValuesResponse(Failure failure, List<InvalidValueParam> invalidValueParams) {
    super(failure);
    problems = invalidValueParams.stream()
            .map(p -> new InvalidValueParamResponse(p, p.getField())).toList();
  }
}
