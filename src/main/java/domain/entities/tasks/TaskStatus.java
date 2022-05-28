package domain.entities.tasks;

import lombok.Getter;

/**
 * Статус задачи.
 */
public enum TaskStatus {
  /**
   * В работе.
   */
  IN_WORK("В работе"),
  /**
   * Ожидает согласование.
   */
  FEEDBACK("Ожидает согласование"),
  /**
   * Выполнено.
   */
  APPROVED("Выполнено");

  /**
   * Название статуса.
   *
   * @return Название статуса задачи.
   */
  private final @Getter String name;

  TaskStatus(String name) {
    this.name = name;
  }
}
