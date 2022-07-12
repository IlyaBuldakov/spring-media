package com.htc.application.dto.responsestatus;

import com.htc.domain.entities.failures.Failure;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class InternalServerErrorResponse extends ApplicationFailureException {
  /**
   * Создаёт экземпляр класса {@link InternalServerErrorResponse}.
   *
   * @param failure Ошибка доменного слоя.
   */
  public InternalServerErrorResponse(Failure failure) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, failure.getMessage());
  }
}