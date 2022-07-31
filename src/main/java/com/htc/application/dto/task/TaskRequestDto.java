package com.htc.application.dto.task;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Entity;
import com.htc.domain.entities.Task;
import java.time.LocalDateTime;

/**
 * Представление запроса задачи.
 *
 * @param name Заголовок задачи.
 * @param contentType Требуемый тип контента.
 * @param description Описание задачи.
 * @param author Идентификатор автора задачи.
 * @param executor Идентификатор исполнителя задачи.
 * @param dateExpired Срок выполнения задачи.
 */
public record TaskRequestDto(
    String name,
    Content.Type contentType,
    String description,
    int author,
    int executor,
    LocalDateTime dateExpired)
    implements Entity.View<TaskRequestDto, Task> {
  @Override
  public TaskRequestDto fromEntity(Task task) {
    return new TaskRequestDto(task);
  }

  /**
   * Создаёт экземпляр класса {@link TaskRequestDto}.
   *
   * @param task Сущность пользователя.
   */
  public TaskRequestDto(Task task) {
    this(task.name().getValue(),
        task.contentType(),
        task.description().getValue(),
        task.author().id().getValue(),
        task.executor().id().getValue(),
        task.dateExpired());
  }
}