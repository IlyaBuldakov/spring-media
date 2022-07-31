package com.htc.application.dto.task;

import com.htc.application.dto.content.ContentTypeDto;
import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.Entity;
import com.htc.domain.entities.Task;
import java.time.LocalDateTime;

/**
 * Представление списка задач.
 *
 * @param id Идентификатор задачи.
 * @param name Заголовок задачи.
 * @param contentType Требуемый тип контента.
 * @param executor Исполнитель задачи.
 * @param dateExpired Срок выполнения задачи.
 * @param status Статус задачи.
 */
public record TaskListItemResponse(
    int id,
    String name,
    ContentTypeDto contentType,
    UserShortResponse executor,
    LocalDateTime dateExpired,
    TaskStatusDto status)
    implements Entity.View<TaskListItemResponse, Task> {
  @Override
  public TaskListItemResponse fromEntity(Task task) {
    return new TaskListItemResponse(task);
  }

  /**
   * Создаёт экземпляр класса {@link TaskListItemResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskListItemResponse(Task task) {
    this(
        task.id().getValue(),
        task.name().getValue(),
        new ContentTypeDto(task.contentType()),
        new UserShortResponse(task.executor()),
        task.dateExpired(),
        new TaskStatusDto(task.status())
    );
  }
}
