package com.htc.application.dto.failures;

import com.htc.domain.entities.failures.Failure;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class InternalServerErrorResponse extends ApplicationFailureException {
  /**
   * Сообщение по умолчанию.
   */
  private static final String DEFAULT_MESSAGE =
      "Запрос пользователя содержит некорректные значения.";

  /**
   * Создаёт экземпляр класса {@link InternalServerErrorResponse}.
   */
  public InternalServerErrorResponse() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
  }

  /**
   * Создаёт экземпляр класса {@link InternalServerErrorResponse}.
   *
   * @param failure Ошибка доменного слоя.
   */
  public InternalServerErrorResponse(Failure failure) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, failure.getMessage());
  }
}
