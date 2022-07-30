package com.htc.application.dto.task;

import com.htc.application.dto.content.ContentTypeDto;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.Entity;
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
public record TaskResponse(
    int id,
    String name,
    ContentTypeDto contentType,
    String description,
    UserResponse author,
    UserResponse executor,
    LocalDateTime dateCreated,
    LocalDateTime dateExpired,
    TaskStatusDto status)
    implements Entity.View<TaskResponse, Task> {
  @Override
  public TaskResponse fromEntity(Task task) {
    return new TaskResponse(task);
  }

  /**
   * Создаёт экземпляр класса {@link TaskResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskResponse(Task task) {
    this(task.id().getValue(),
        task.name().getValue(),
        new ContentTypeDto(task.contentType()),
        task.description().getValue(),
        new UserResponse(task.author()),
        new UserResponse(task.executor()),
        task.dateCreated(),
        task.dateExpired(),
        new TaskStatusDto(task.status()));
  }
}
