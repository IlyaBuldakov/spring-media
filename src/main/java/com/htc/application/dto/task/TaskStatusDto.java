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
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Название статуса.
   *
   * @return name Название статуса.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Создаёт экземпляр класса {@link TaskStatusDto}.
   *
   * @param taskStatus Статус задачи.
   */
  public TaskStatusDto(Task.Status taskStatus) {
    this.id = taskStatus.getId();
    this.name = taskStatus.getName();
  }
}
