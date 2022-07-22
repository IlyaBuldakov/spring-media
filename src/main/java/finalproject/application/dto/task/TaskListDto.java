package finalproject.application.dto.task;

import finalproject.application.dto.content.ContentTypeDto;
import finalproject.application.dto.user.UserDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Представление задачи для списка задач.
 */
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

  /**
   * Конструктор представления задачи для списка задач.
   *
   * @param id идентификатор
   * @param name имя
   * @param type тип контента
   * @param executor исполнитель / контент-менеджер
   * @param dateExpired дата выполнения
   * @param status статус задачи
   */
  public TaskListDto(int id, String name, ContentTypeDto type, UserDto executor,
                     LocalDateTime dateExpired, TaskStatusDto status) {
    super(id, name);
    this.type = type;
    this.executor = executor;
    this.dateExpired = dateExpired;
    this.status = status;
  }
}
