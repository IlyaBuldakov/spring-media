package com.htc.application.dto.task;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import java.time.LocalDateTime;

/**
 * Представление сущности задачи (запрос).
 */
public class TaskRequest {
  /**
   * Название задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String name;

  /**
   * Тип контента задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  public Content.Type type;

  /**
   * Описание задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String description;

  /**
   * Идентификатор автора задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  public int authorId;

  /**
   * Идентификатор исполнителя задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  public int executorId;

  /**
   * Дата окончания задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  public LocalDateTime dateExpired;

  /**
   * Статус задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  public Task.Status status;

  private TaskRequest() {}

  /**
   * Создает экземпляр класса {@link TaskRequest}.
   *
   * @param task Сущность задачи.
   */
  public TaskRequest(Task task) {
    this.name = task.getName();
    this.type = task.getContentType();
    this.description = task.getDescription();
    this.authorId = task.getAuthorId();
    this.executorId = task.executorId();
    this.dateExpired = task.getDateExpired();
    this.status = task.getStatus();
  }
}
