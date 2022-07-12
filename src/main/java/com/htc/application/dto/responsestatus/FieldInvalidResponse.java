package com.htc.application.dto.responsestatus;

import com.htc.domain.entities.failures.InvalidValue;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class FieldInvalidResponse extends ApplicationFailureException {
  /**
   * Связанное с ошибкой поле интерфейса.
   *
   * @return message Сообщение ошибки.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String field;

  /**
   * Создаёт экземпляр класса {@link FieldInvalidResponse}.
   *
   * @param failure Ошибка доменного слоя.
   */
  public FieldInvalidResponse(InvalidValue failure) {
    super(HttpStatus.BAD_REQUEST, failure.getMessage());

    this.field = failure.getField();
  }
}