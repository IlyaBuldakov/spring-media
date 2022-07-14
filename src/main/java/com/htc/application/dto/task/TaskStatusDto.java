package com.htc.application.dto.task;

import com.htc.domain.entities.tasks.Task;
import lombok.Getter;

/**
 * Представление статуса задач.
 */
public class TaskStatusDto {
  /**
   * Идентификатор статуса.
   *
   * @return id Идентификатор статуса.
   */
  private final @Getter int id;

  /**
   * Название статуса.
   *
   * @return name Название статуса.
   */
  private final @Getter String name;

  /**
   * Создаёт экземпляр класса {@link TaskStatusDto}.
   *
   * @param taskStatus Статус задачи.
   */
  public TaskStatusDto(Task.TaskStatus taskStatus) {
    this.id = taskStatus.getId();
    this.name = taskStatus.getName();
  }
}
