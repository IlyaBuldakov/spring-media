package com.htc.application.dtos.task;

import com.htc.domain.entities.task.Task;
import lombok.Getter;

/**
 * Ответ. Краткое представление задачи.
 */
public class TaskShortResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter long id;

  /**
   * Наименование задачи.
   *
   * @return name наименование задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Создание краткого представления задачи.
   *
   * @param task сущность задачи, подробнее {@link Task}
   */
  public TaskShortResponse(Task task) {
    this.id = task.getId().getValue();
    this.name = task.getName().getValue();
  }
}
