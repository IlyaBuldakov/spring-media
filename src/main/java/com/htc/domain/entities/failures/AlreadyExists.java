package com.htc.domain.entities.failures;

import org.springframework.http.HttpStatus;

/**
 * Сущность уже сушествует.
 */
public enum AlreadyExists implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(HttpStatus.FORBIDDEN, "Сущность уже существует.");

  private final HttpStatus status;
  private final String message;

  AlreadyExists(HttpStatus status, String message) {
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
