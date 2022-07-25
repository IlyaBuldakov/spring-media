package com.htc.application.dto.task;

import com.htc.application.dto.content.ContentTypeDto;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.Task;
import java.time.LocalDateTime;

/**
 * Представление сущности задачи.
 *
 * @param id Индентификатор задачи.
 * @param name Заголовок задачи.
 * @param contentType Требуемный тип контента.
 * @param description Описание задачи.
 * @param author Автор задачи.
 * @param executor Исполнитель задачи.
 * @param dateCreated Дата создания задачи.
 * @param dateExpired Срок выполнения задачи.
 * @param status Статус задачи.
 */
public record TaskResponse(int id,
                           String name,
                           ContentTypeDto contentType,
                           String description,
                           UserResponse author,
                           UserResponse executor,
                           LocalDateTime dateCreated,
                           LocalDateTime dateExpired,
                           TaskStatusDto status) {
  /**
   * Создаёт экземпляр класса {@link TaskResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskResponse(Task task) {
    this(task.getId().getValue(),
            task.getName().getValue(),
            new ContentTypeDto(task.getContentType()),
            task.getDescription().getValue(),
            new UserResponse(task.getAuthor()),
            new UserResponse(task.getExecutor()),
            task.getDateCreated(),
            task.getDateExpired(),
            new TaskStatusDto(task.getStatus()));
  }
}
