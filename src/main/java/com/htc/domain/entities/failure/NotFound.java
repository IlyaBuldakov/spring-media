package com.htc.domain.entities.failure;

import lombok.Getter;

/**
 * Ошибка "Не найдено".
 */
public enum NotFound implements Failure {

  USER("Пользователь не найден"),

  TASK("Задача не найдена"),

  FILE("Файл не найден"),

  CONTENT("Контент не найден");

  NotFound(String message) {
    this.message = message;
  }

  /**
   * Сообщение об ошибке.
   */
  private final @Getter String message;
}
