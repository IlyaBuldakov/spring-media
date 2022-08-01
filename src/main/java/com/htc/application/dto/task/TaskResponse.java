package com.htc.application.dto.task;

import com.htc.application.dto.content.TypeResponse;
import com.htc.domain.entities.Task;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности задачи (ответ на запрос).
 */
public class TaskResponse {
  /**
   * Получает идентификатор задачи.
   *
   * @return id Идентификатор задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Получает название задачи.
   *
   * @return name Название задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Получает тип контента задачи.
   *
   * @return type Имя пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter TypeResponse type;

  /**
   * Получает описание задачи.
   *
   * @return description Описание задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String description;

  /**
   * Получает идентификатор автора задачи.
   *
   * @return authorId Идентификатор автора задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int authorId;

  /**
   * Получает идентификатор исполнителя задачи.
   *
   * @return executorId Идентификатор исполнителя задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int executorId;

  /**
   * Получает дату завершения задачи.
   *
   * @return dateExpired Дата завершения задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateExpired;

  /**
   * Создает экземпляр класса {@link TaskResponse}.
   *
   * @param task Сущность задачи.
   */
  public TaskResponse(Task task) {
    this.id = task.getId().getValue();
    this.name = task.getName();
    this.type = new TypeResponse(task.getContentType());
    this.description = task.getDescription();
    this.authorId = task.getAuthorId();
    this.executorId = task.executorId();
    this.dateExpired = task.getDateExpired();
  }
}
