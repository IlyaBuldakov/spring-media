package com.htc.domain.entities.failures;

/**
 * Ошибка.
 */
public interface Failure {

  /**
   * Получить код ошибки.
   *
   * @return status - код ошибки
   */
  int getStatus();

  /**
   * Получить сообщение.
   *
   * @return message - сообщение об ошибке
   */
  String getMessage();
}
