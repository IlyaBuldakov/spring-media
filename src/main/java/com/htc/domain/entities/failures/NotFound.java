package com.htc.domain.entities.failures;

/**
 * Сущность не найдена.
 */
public enum NotFound implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  DEFAULT_MESSAGE(-1, "Сущность не найдена.");

  private final int status;
  private final String message;

  NotFound(int status, String message) {
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

  //написать тест что наследуется от failure

}
