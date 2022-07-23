package com.htc.application.dto.task;

import com.htc.application.dto.content.ContentTypeDto;
import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.tasks.Task;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление списка задач.
 */
public class TaskListItemResponse {
  /**
   * Индентификатор задачи.
   *
   * @return Индентификатор задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int id;
  /**
   * Заголовок задачи.
   *
   * @return заголовок задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;
  /**
   * Требуемный тип контента.
   *
   * @return Тип контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter ContentTypeDto contentType;
  /**
   * Исполнитель задачи.
   *
   * @return Позьзователь - исполнителя задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserShortResponse executor;
  /**
   * Срок выполнения задачи.
   *
   * @return Срок выполнения задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateExpired;
  /**
   * Статус задачи.
   *
   * @return Статус задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter TaskStatusDto status;

  /**
   * Создаёт экземпляр класса {@link TaskListItemResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskListItemResponse(Task task) {
    this.name = task.getName().getValue();
    this.contentType = new ContentTypeDto(task.getContentType());
    this.executor = new UserShortResponse(task.getExecutor());
    this.dateExpired = task.getDateExpired();
    this.status = new TaskStatusDto(task.getStatus());
  }
}
