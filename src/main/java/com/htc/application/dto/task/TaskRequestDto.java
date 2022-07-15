package com.htc.application.dto.task;

import com.htc.application.dto.content.ContentTypeDto;
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
  private @Getter String name;
  /**
   * Требуемный тип контента.
   *
   * @return Тип контента.
   */
  private @Getter ContentTypeDto contentType;
  /**
   * Описание задачи.
   *
   * @return Описание задачи.
   */
  private @Getter String description;
  /**
   * Индентификатор автора задачи.
   *
   * @return Индентификатор позьзователя - автора задачи.
   */
  private @Getter int author;
  /**
   * Индентификатор исполнителя задачи.
   *
   * @return Индентификатор позьзователя - исполнителя задачи.
   */
  private @Getter int executor;
  /**
   * Срок выполнения задачи.
   *
   * @return Срок выполнения задачи.
   */
  private @Getter LocalDateTime dateExpired;

  /**
   * Создаёт экземпляр класса {@link TaskRequestDto}.
   *
   * @param task Сущность пользователя.
   */
  public TaskRequestDto(Task task) {
    this.name = task.getName().getValue();
    this.contentType = new ContentTypeDto(task.getContentType());
    this.description = task.getDescription().getValue();
    this.author = task.getAuthor().getId().getValue();
    this.executor = task.getExecutor().getId().getValue();
    this.dateExpired = task.getDateExpired();
  }

  public TaskRequestDto() {
  }
}