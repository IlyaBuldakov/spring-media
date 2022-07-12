package com.htc.application.dto.responsestatus;

import com.htc.domain.entities.failures.Failure;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class NotFoundResponse extends ApplicationFailureException {
  /**
   * Создаёт экземпляр класса {@link com.htc.application.dto.responsestatus.NotFoundResponse}.
   *
   * @param failure Ошибка доменного слоя.
   */
  public NotFoundResponse(Failure failure) {
    super(HttpStatus.NOT_FOUND, failure.getMessage());
  }
}