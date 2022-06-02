package finalproject.application.dto.task;

import finalproject.application.dto.content.ContentTypeDto;
import finalproject.application.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class TaskListDto extends TaskBasicDto {

  /**
   * Возвращает @return type тип контента.
   */
  private @Getter ContentTypeDto type;

  /**
   * Возвращает @return UserDto исполнителя задачи.
   */
  private @Getter UserDto executor;

  /**
   * Возвращает @return LocalDateTime дату выполнения задачи.
   */
  private @Getter LocalDateTime dateExpired;

  /**
   * Возвращает @return TaskStatusDto status статус задачи.
   */
  private @Getter TaskStatusDto status;

  public TaskListDto(int id, String name, ContentTypeDto type, UserDto executor,
                     LocalDateTime dateExpired, TaskStatusDto status) {
    super(id, name);
    this.type = type;
    this.executor = executor;
    this.dateExpired = dateExpired;
    this.status = status;
  }
}
