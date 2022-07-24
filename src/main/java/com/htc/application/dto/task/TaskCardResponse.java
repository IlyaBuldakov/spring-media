package com.htc.application.dto.task;

import com.htc.domain.entities.tasks.Task;

/**
 * Краткое представление сущности задачи.
 *
 * @param id Идентификатор задачи.
 * @param name Имя задачи.
 */
public record TaskCardResponse(int id, String name) {
    /**
   * Создаёт экземпляр класса {@link TaskCardResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskCardResponse(Task task) {
    this(task.getId().getValue(), task.getName().getValue());
  }
}
