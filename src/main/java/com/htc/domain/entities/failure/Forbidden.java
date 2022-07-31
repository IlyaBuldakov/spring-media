package com.htc.domain.entities.failure;

import lombok.Getter;

/**
 * Сущность ошибки "У вас нет доступа".
 */
public record Forbidden() implements Failure {

  /**
   * Стандартное сообщение.
   */
  private static final @Getter String message = "У вас нет доступа";
}
