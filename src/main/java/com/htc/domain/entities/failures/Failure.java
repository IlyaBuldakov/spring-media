package com.htc.domain.entities.failures;

import org.springframework.http.HttpStatus;

/**
 * Ошибка.
 */
public interface Failure {
  /**
   * Получить статус ошибки.
   *
   * @return status статус ошибки
   */
  HttpStatus getStatus();

  /**
   * Получить сообщение ошибки.
   *
   * @return message сообщение ошибки
   */
  String getMessage();
}
