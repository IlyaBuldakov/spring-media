package com.htc.application.dto.task;

import com.htc.domain.entities.tasks.Task;
import lombok.Getter;

/**
 * Краткое представление сущности задачи.
 */
public class TaskBasicDto {
  /**
   * Идентификатор задачи.
   *
   * @return id Идентификатор задачи.
   */
  private final @Getter int id;

  /**
   * Имя задачи.
   *
   * @return name Имя задачи.
   */
  private final @Getter String name;

  /**
   * Создаёт экземпляр класса {@link TaskBasicDto}.
   *
   * @param task Сущность пользователя.
   */
  public TaskBasicDto(Task task) {
    this.id = task.getId();
    this.name = task.getName();
  }
}
