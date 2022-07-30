package finalproject.application.dto.task;

import finalproject.application.dto.content.ContentTypeDto;
import finalproject.application.dto.user.UserDto;
import finalproject.domain.entities.task.Task;
import lombok.Getter;

/**
 * Представление задачи для списка задач.
 */

public class TaskListDto extends TaskBasicDto {

  /**
   * Возвращает @return type тип контента.
   */
  private final @Getter ContentTypeDto type;

  /**
   * Возвращает @return UserDto исполнителя задачи.
   */
  private final @Getter UserDto executor;

  /**
   * Возвращает @return LocalDateTime дату выполнения задачи.
   */
  private final @Getter String dateExpired;

  /**
   * Возвращает @return TaskStatusDto status статус задачи.
   */
  private final @Getter TaskStatusDto status;

  /**
   * Конструктор представления задачи для списка задач.
   *
   * @param task задача - сущность доменного слоя
   */
  public TaskListDto(Task task) {
    super(task);
    this.dateExpired = task.getDateExpired().toString();
    this.executor = new UserDto(task.getContentMaker());
    this.type = new ContentTypeDto(task.getType().getId(), task.getType().getName());
    this.status = new TaskStatusDto(task.getTaskStatus().getId(), task.getTaskStatus().getName());


  }
}
