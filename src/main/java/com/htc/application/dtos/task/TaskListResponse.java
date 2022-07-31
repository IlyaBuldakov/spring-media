package com.htc.application.dtos.task;

import com.htc.application.dtos.content.TypeResponse;
import com.htc.application.dtos.user.UserResponse;
import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.task.Status;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Представление задачи для списка.
 */
@EqualsAndHashCode
public class TaskListResponse {
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
   * {@link Type Тип} контента.
   *
   * @return type тип
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter TypeResponse type;

  /**
   * {@link User Исполнитель задачи}.
   *
   * @return executor исполнитель задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserResponse executor;

  /**
   * Дата выполнения.
   *
   * @return dateExpired дата выполнения
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String dateExpired;

  /**
   * {@link Status Статус} задачи.
   *
   * @return status статус задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter StatusResponse status;

  /**
   * Создание краткого представления задачи.
   *
   * @param task сущность задачи, подробнее {@link Task}
   */
  public TaskListResponse(Task task) {
    this.id = task.getId().getValue();
    this.name = task.getName().getValue();
    this.type = new TypeResponse(task.getType());
    this.executor = new UserResponse(task.getExecutor());
    this.dateExpired = task.getDateExpired().getValue();
    this.status = new StatusResponse(task.getStatus());
  }
}