package com.htc.domain.entities.tasks;

import lombok.Getter;

/**
 * Статус задачи.
 */
public enum TaskStatus {
  /**
   * В работе.
   */
  IN_WORK("В работе", 1),
  /**
   * Ожидает согласование.
   */
  FEEDBACK("Ожидает согласование", 2),
  /**
   * Выполнено.
   */
  APPROVED("Выполнено", 3);

  /**
   * Идентификатор статуса.
   *
   * @return id Идентификатор статуса.
   */
  private final @Getter int id;

  /**
   * Название статуса.
   *
   * @return Название статуса задачи.
   */
  private final @Getter String name;

  TaskStatus(String name, int id) {
    this.name = name;
    this.id = id;
  }
}
