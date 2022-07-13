package com.htc.application.dto.failures;

import com.htc.domain.entities.failures.Failure;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class NotFoundResponse extends ApplicationFailureException {
  /**
   * Создаёт экземпляр класса {@link com.htc.application.dto.failures.NotFoundResponse}.
   *
   * @param failure Ошибка доменного слоя.
   */
  public NotFoundResponse(Failure failure) {
    super(HttpStatus.NOT_FOUND, failure.getMessage());
  }
}
