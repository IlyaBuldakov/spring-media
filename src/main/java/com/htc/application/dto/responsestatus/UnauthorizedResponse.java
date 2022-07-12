package com.htc.application.dto.responsestatus;

import com.htc.domain.entities.failures.Failure;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class UnauthorizedResponse extends ApplicationFailureException {
  /**
   * Создаёт экземпляр класса {@link UnauthorizedResponse}.
   *
   * @param failure Ошибка доменного слоя.
   */
  public UnauthorizedResponse(Failure failure) {
    super(HttpStatus.UNAUTHORIZED, failure.getMessage());
  }
}