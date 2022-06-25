package com.htc.application.dtos.exceptions;

import com.htc.domain.entities.failures.InvalidValueParam;
import lombok.Getter;

/**
 * Представление ошибки параметра
 * {@link com.htc.domain.entities.failures.InvalidValueParam InvalidValueParam}.
 */
public class InvalidValueParamResponse {
  /**
   * Текст ошибки.
   *
   * @return message сообщение ошибки
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String message;

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
   * @param invalidValueParam сообщение ошибки, подробнее {@link InvalidValueParam}
   * @param field поле, в котором возникла ошибка
   */
  public InvalidValueParamResponse(InvalidValueParam invalidValueParam, String field) {
    this.message = invalidValueParam.getMessage();
    this.field = field;
  }
}
