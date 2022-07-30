package com.htc.application.dto.task;

import com.htc.domain.entities.Entity;
import com.htc.domain.entities.Task;

/**
 * Краткое представление сущности задачи.
 *
 * @param id Идентификатор задачи.
 * @param name Имя задачи.
 */
public record TaskCardResponse(int id, String name)
    implements Entity.View<TaskCardResponse, Task> {
  @Override
  public TaskCardResponse fromEntity(Task task) {
    return new TaskCardResponse(task);
  }
    /**
   * Создаёт экземпляр класса {@link TaskCardResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskCardResponse(Task task) {
    this(task.id().getValue(), task.name().getValue());
  }
}
