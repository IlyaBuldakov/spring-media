package com.htc.application.dto.task;

import com.htc.application.dto.content.ContentTypeDto;
import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.tasks.Task;
import java.time.LocalDateTime;

/**
 * Представление списка задач.
 *
 * @param id Индентификатор задачи.
 * @param name Заголовок задачи.
 * @param contentType Требуемный тип контента.
 * @param executor Исполнитель задачи.
 * @param dateExpired Срок выполнения задачи.
 * @param status Статус задачи.
 */
public record TaskListItemResponse(int id,
                                   String name,
                                   ContentTypeDto contentType,
                                   UserShortResponse executor,
                                   LocalDateTime dateExpired,
                                   TaskStatusDto status) {
  /**
   * Создаёт экземпляр класса {@link TaskListItemResponse}.
   *
   * @param task Сущность пользователя.
   */
  public TaskListItemResponse(Task task) {
    this(task.getId().getValue(),
            task.getName().getValue(),
            new ContentTypeDto(task.getContentType()),
            new UserShortResponse(task.getExecutor()),
            task.getDateExpired(),
            new TaskStatusDto(task.getStatus()));
  }
}
