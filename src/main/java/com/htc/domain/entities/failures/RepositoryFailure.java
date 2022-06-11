package com.htc.domain.entities.failures;

/**
 * ошибка репозитория.
 */
public enum RepositoryFailure implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(-1, "Ошибка репозитория.");

  private final int status;
  private final String message;

  RepositoryFailure(Integer status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override
  public int getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
