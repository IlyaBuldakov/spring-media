package com.htc.application.dto.responsestatus;

import com.htc.domain.entities.failures.Failure;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class BadRequestResponse extends ApplicationFailureException {
  /**
   * Связанное с ошибкой поля интерфейса.
   *
   * @return message Сообщение ошибки.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Iterable<FieldInvalidResponse> problems;

  /**
   * Создаёт экземпляр класса {@link BadRequestResponse}.
   *
   * @param failure  Ошибка доменного слоя.
   * @param problems Связанное с ошибкой поля интерфейса.
   */
  public BadRequestResponse(Failure failure, Iterable<FieldInvalidResponse> problems) {
    super(HttpStatus.BAD_REQUEST, failure);

    this.problems = problems;
  }
}