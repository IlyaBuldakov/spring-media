package com.htc.application.dto.task;

import com.htc.domain.entities.tasks.Task;
import lombok.Getter;

/**
 * Краткое представление сущности задачи.
 */
public class TaskCardResponse {
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
   * Создаёт экземпляр класса {@link TaskCardResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskCardResponse(Task task) {
    this.id = task.getId().getValue();
    this.name = task.getName().getValue();
  }
}
