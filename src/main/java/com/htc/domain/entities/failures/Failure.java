package com.htc.domain.entities.failures;

import lombok.Getter;

/**
 * Ошибка.
 */
public interface Failure {

  /**
   * Получить сообщение ошибки.
   *
   * @return Сообщение ошибки.
   */
  String getMessage();
}
