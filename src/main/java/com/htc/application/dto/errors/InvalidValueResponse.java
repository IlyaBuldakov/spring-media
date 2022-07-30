package com.htc.application.dto.errors;

import com.htc.domain.entities.failure.InvalidValue;
import lombok.Getter;

/**
 * Представление ошибки невалидного поля.
 * Используется в качестве причины {@link BadRequestResponse}.
 */
public class InvalidValueResponse {

  /**
   * Поле, в котором обнаружена ошибка.
   */
  private final @Getter String field;

  /**
   * Сообщение ошибки.
   */
  private final @Getter String message;

  /**
   * Конструктор из {@link InvalidValue соответствующей сущности}.
   *
   * @param invalidValue Сущность {@link InvalidValue ошибки} невалидного поля.
   */
  public InvalidValueResponse(InvalidValue invalidValue) {
    this.field = invalidValue.getField();
    this.message = invalidValue.getMessage();
  }
}
