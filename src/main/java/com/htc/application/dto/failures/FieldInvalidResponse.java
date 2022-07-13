package com.htc.application.dto.failures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.htc.domain.entities.failures.Failure;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
@JsonIgnoreProperties(value = { "statusCode" })
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
   * @param field Связанное с ошибкой поле интерфейса.
   */
  public FieldInvalidResponse(Failure failure, String field) {
    super(HttpStatus.BAD_REQUEST, failure.getMessage());
    this.field = field;
  }
}
