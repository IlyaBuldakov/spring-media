package com.htc.application.dto.task;

import com.htc.application.dto.content.ContentTypeDto;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.tasks.Task;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление списка задач.
 */
public class TaskListDto {
  /**
   * Индентификатор задачи.
   *
   * @return Индентификатор задачи.
   */
  private @Getter int id;
  /**
   * Заголовок задачи.
   *
   * @return заголовок задачи.
   */
  private final @Getter String name;
  /**
   * Требуемный тип контента.
   *
   * @return Тип контента.
   */
  private final @Getter ContentTypeDto contentType;
  /**
   * Исполнитель задачи.
   *
   * @return Позьзователь - исполнителя задачи.
   */
  private final @Getter UserResponse executor;
  /**
   * Срок выполнения задачи.
   *
   * @return Срок выполнения задачи.
   */
  private final @Getter LocalDateTime dateExpired;
  /**
   * Статус задачи.
   *
   * @return Статус задачи.
   */
  private final @Getter TaskStatusDto status;

  /**
   * Создаёт экземпляр класса {@link TaskListDto}.
   *
   * @param task Сущность пользователя.
   */
  public TaskListDto(Task task) {
    this.name = task.getName().getValue();
    this.contentType = new ContentTypeDto(task.getContentType());
    this.executor = new UserResponse(task.getExecutor());
    this.dateExpired = task.getDateExpired();
    this.status = new TaskStatusDto(task.getStatus());
  }
}
