package com.htc.application.dto.responsestatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.htc.domain.entities.failures.Failure;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Общий класс ошибок слоя приложения.
 */
@JsonIgnoreProperties(value = {
  "cause", "stackTrace", "suppressed", "localizedMessage" })
public abstract class ApplicationFailureException extends RuntimeException {
  /**
   * Код статуса.
   *
   * @return statusCode Код статуса.
   */
  private final @Getter @JsonIgnore HttpStatus status;

  /**
   * Исходная ошибка.
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
   * Создаёт экземпляр класса {@link ApplicationFailureException}.
   *
   * @param status  Код статуса.
   * @param failure Исходная ошибка доменного слоя.
   */
  public ApplicationFailureException(HttpStatus status, Failure failure) {
    this.status = status;
    this.statusCode = status.value();
    this.message = failure.getMessage();
  }
}
