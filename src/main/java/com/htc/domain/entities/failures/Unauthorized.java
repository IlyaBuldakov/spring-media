package com.htc.domain.entities.failures;

import org.springframework.http.HttpStatus;

/**
 * Ошибка недостаточности прав.
 */
public enum Unauthorized implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(HttpStatus.UNAUTHORIZED, "Недостаточно прав для выполнения операции.");

  private final HttpStatus status;
  private final String message;

  Unauthorized(HttpStatus status, String message) {
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
