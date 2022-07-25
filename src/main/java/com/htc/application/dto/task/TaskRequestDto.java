package com.htc.application.dto.task;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import java.time.LocalDateTime;

/**
 * Представление запроса задачи.
 *
 * @param name Заголовок задачи.
 * @param contentType Требуемный тип контента.
 * @param description Описание задачи.
 * @param author Индентификатор автора задачи.
 * @param executor Индентификатор исполнителя задачи.
 * @param dateExpired Срок выполнения задачи.
 */
public record TaskRequestDto(
        String name,
        Content.Type contentType,
        String description,
        int author,
        int executor,
        LocalDateTime dateExpired) {

  /**
   * Создаёт экземпляр класса {@link TaskRequestDto}.
   *
   * @param task Сущность пользователя.
   */
  public TaskRequestDto(Task task) {
    this(task.getName().getValue(),
            task.getContentType(),
            task.getDescription().getValue(),
            task.getAuthor().getId().getValue(),
            task.getExecutor().getId().getValue(),
            task.getDateExpired());
  }
}