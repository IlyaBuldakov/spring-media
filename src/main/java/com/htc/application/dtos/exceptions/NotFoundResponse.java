package com.htc.application.dtos.exceptions;

import com.htc.domain.entities.failures.Failure;

/**
 * Ответ. Представление ошибки {@link com.htc.domain.entities.failures.NotFound NotFound}.
 */
public class NotFoundResponse extends CustomResponseStatusException {
  /**
   * Создание представления ошибки.
   *
   * @param failure сущность ошибки, подробнее {@link Failure}
   */
  public NotFoundResponse(Failure failure) {
    super(failure);
  }
}
