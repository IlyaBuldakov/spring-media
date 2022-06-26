package com.htc.application.dto.responsestatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.htc.domain.entities.failures.Failure;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Общий класс ошибок слоя приложения.
 */
public class ApplicationFailureException extends RuntimeException {
  /**
   * Код статуса.
   *
   * @return statusCode Код статуса.
   */
  private final @Getter @JsonIgnore HttpStatus status;

  /**
   * Исходная ошибка.
   *
   * @return message Исходная ошибка.
   */
  private final @Getter @JsonIgnore Failure failure;

  public ApplicationFailureException(HttpStatus status, Failure failure) {
    this.status = status;
    this.failure = failure;
  }
}
