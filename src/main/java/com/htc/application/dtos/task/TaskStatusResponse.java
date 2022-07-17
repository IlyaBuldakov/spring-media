package com.htc.application.dtos.task;

import com.htc.domain.entities.task.Status;
import com.htc.domain.entities.task.Task;
import lombok.Getter;

/**
 * Ответ. Представление задачи для проверки статуса.
 */
public class TaskStatusResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter long id;

  /**
   * {@link Status Статус} задачи.
   *
   * @return status статус задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Status status;

  /**
   * Создание краткого представления задачи.
   *
   * @param task сущность задачи, подробнее {@link Task}
   */
  public TaskStatusResponse(Task task) {
    this.id = task.getId().getValue();
    this.status = task.getStatus();
  }
}
