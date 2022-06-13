package com.htc.domain.entities.task;

import lombok.Getter;

/**
 * Статус задачи.
 */
public enum TaskStatus {

  /**
   * Одобрено.
   */
  APPROVED(3, "Одобрено"),
  /**
   * Обратная связь.
   */
  FEEDBACK(2, "Обратная связь"),
  /**
   * В работе.
   */
  IN_WORK(1, "В работе");

  /**
   * Идентификатор статуса задачи.
   *
   * @return id Идентификатор статуса задачи.
   */
  private final @Getter int id;
  /**
   * Название статуса задачи.
   *
   * @return name Название статуса задачи.
   */
  private final @Getter String name;

  TaskStatus(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
