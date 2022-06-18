package com.htc.application.dtos.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.htc.domain.entities.failures.Failure;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Ответ. Представление ошибки.
 */
@JsonIgnoreProperties(value = {"cause", "stackTrace", "suppressed", "localizedMessage"})
public abstract class CustomResponseStatusException extends RuntimeException {
  private final @Getter int statusCode;
  private final @Getter @JsonIgnore HttpStatus status;
  private final @Getter String message;

  /**
   * Создание представления ошибки.
   *
   * @param failure сущность ошибки
   */
  public CustomResponseStatusException(Failure failure) {
    this.status = failure.getStatus();
    this.statusCode = this.status.value();
    this.message = failure.getMessage();
  }
}
