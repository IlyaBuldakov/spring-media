package com.htc.application.dto.task;

import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.tasks.Task;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление запроса задачи.
 */
public class TaskRequestDto {
  /**
   * Заголовок задачи.
   *
   * @return заголовок задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String name;
  /**
   * Требуемный тип контента.
   *
   * @return Тип контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Content.Type contentType;
  /**
   * Описание задачи.
   *
   * @return Описание задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String description;
  /**
   * Индентификатор автора задачи.
   *
   * @return Индентификатор позьзователя - автора задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int author;
  /**
   * Индентификатор исполнителя задачи.
   *
   * @return Индентификатор позьзователя - исполнителя задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int executor;
  /**
   * Срок выполнения задачи.
   *
   * @return Срок выполнения задачи.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter LocalDateTime dateExpired;

  /**
   * Создаёт экземпляр класса {@link TaskRequestDto}.
   *
   * @param task Сущность пользователя.
   */
  public TaskRequestDto(Task task) {
    this.name = task.getName().getValue();
    this.contentType = task.getContentType();
    this.description = task.getDescription().getValue();
    this.author = task.getAuthor().getId().getValue();
    this.executor = task.getExecutor().getId().getValue();
    this.dateExpired = task.getDateExpired();
  }

  public TaskRequestDto() {
  }
}