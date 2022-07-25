package com.htc.application.dto.task;

import com.htc.domain.entities.Task;

/**
 * Представление статуса задач.
 *
 * @param id Идентификатор статуса.
 * @param name Название статуса.
 */
public record TaskStatusDto(int id, String name) {
    /**
   * Создаёт экземпляр класса {@link TaskStatusDto}.
   *
   * @param taskStatus Статус задачи.
   */
  public TaskStatusDto(Task.Status taskStatus) {
    this(taskStatus.getId(), taskStatus.getName());
  }
}
