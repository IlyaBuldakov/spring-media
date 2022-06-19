package com.htc.application.dtos.exceptions;

import com.htc.domain.entities.failures.Failure;
import lombok.Getter;

/**
 * Ответ. Представление ошибки
 * {@link com.htc.domain.entities.failures.InvalidValueParam InvalidValueParam}.
 */
public class InvalidValueParamResponse extends CustomResponseStatusException {
  /**
   * Наименование поля, в котором возникла ошибка.
   *
   * @return field наименование поля
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String field;

  /**
   * Создание представления ошибки.
   *
   * @param failure сущность ошибки, подробнее {@link Failure}
   */
  public InvalidValueParamResponse(Failure failure, String field) {
    super(failure);
    this.field = field;
  }
}
