package com.htc.application.dto.task;

import com.htc.application.dto.content.ContentTypeDto;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.tasks.Task;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности задачи.
 */
public class TaskResponse {
  /**
   * Индентификатор задачи.
   *
   * @return Индентификатор задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;
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
   * Описание задачи.
   *
   * @return Описание задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String description;

  /**
   * Автор задачи.
   *
   * @return Позьзователя - автора задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserResponse author;
  /**
   * Исполнитель задачи.
   *
   * @return Позьзователя - исполнителя задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserResponse executor;
  /**
   * Дата создания задачи.
   *
   * @return Дату создания задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateCreated;
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
   * Создаёт экземпляр класса {@link TaskResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskResponse(Task task) {
    this.id = task.getId().getValue();
    this.name = task.getName().getValue();
    this.contentType = new ContentTypeDto(task.getContentType());
    this.description = task.getDescription().getValue();
    this.author = new UserResponse(task.getAuthor());
    this.executor = new UserResponse(task.getExecutor());
    this.dateCreated = task.getDateCreated();
    this.dateExpired = task.getDateExpired();
    this.status = new TaskStatusDto(task.getStatus());
  }
}
