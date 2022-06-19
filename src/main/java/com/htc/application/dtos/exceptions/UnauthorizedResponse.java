package com.htc.application.dtos.exceptions;

import com.htc.domain.entities.failures.Failure;

/**
 * Ответ. Представление ошибки {@link com.htc.domain.entities.failures.Unauthorized Unauthorized}.
 */
public class UnauthorizedResponse extends CustomResponseStatusException {
  /**
   * Создание представления ошибки.
   *
   * @param failure сущность ошибки, подробнее {@link Failure}
   */
  public UnauthorizedResponse(Failure failure) {
    super(failure);
  }
}
