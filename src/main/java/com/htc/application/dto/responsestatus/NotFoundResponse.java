package com.htc.application.dto.responsestatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.htc.domain.entities.failures.Failure;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
@JsonIgnoreProperties(value = {"cause", "stackTrace", "suppressed", "localizedMessage" })
public class NotFoundResponse extends ApplicationFailureException {
  /**
   * Код статуса.
   *
   * @return statusCode Код статуса.
   */
  private final @Getter int statusCode;

  /**
   * Сообщение ошибки.
   *
   * @return message Сообщение ошибки.
   */
  private final @Getter String message;

  /**
   * Создаёт экземпляр класса {@link com.htc.application.dto.responsestatus.NotFoundResponse}.
   *
   * @param failure Ошибка доменного слоя.
   */
  public NotFoundResponse(Failure failure) {
    super(HttpStatus.NOT_FOUND, failure);

    this.statusCode = this.getStatus().value();
    this.message = failure.getMessage();
  }
}