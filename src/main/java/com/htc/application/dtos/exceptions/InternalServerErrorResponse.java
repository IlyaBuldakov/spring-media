package com.htc.application.dtos.exceptions;

import com.htc.domain.entities.failures.Failure;

/**
 * Ответ. Представление внутренней ошибки.
 */
public class InternalServerErrorResponse extends CustomResponseStatusException {
  /**
   * Создание представления ошибки.
   *
   * @param failure сущность ошибки, подробнее {@link Failure}
   */
  public InternalServerErrorResponse(Failure failure) {
    super(failure);
  }
}
