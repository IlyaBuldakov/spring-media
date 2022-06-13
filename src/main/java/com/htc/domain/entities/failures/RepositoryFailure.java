package com.htc.domain.entities.failures;

import org.springframework.http.HttpStatus;

/**
 * Ошибка репозитория.
 */
public enum RepositoryFailure implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка репозитория.");

  private final HttpStatus status;
  private final String message;

  RepositoryFailure(HttpStatus status, String message) {
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
