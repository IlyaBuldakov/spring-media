package com.htc.domain.entities.failures;

import org.springframework.http.HttpStatus;

/**
 * Сущность не найдена.
 */
public enum NotFound implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(HttpStatus.NOT_FOUND, "Сущность не найдена.");

  private final HttpStatus status;
  private final String message;

  NotFound(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override
  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
