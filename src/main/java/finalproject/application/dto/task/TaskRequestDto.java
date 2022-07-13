package finalproject.application.dto.task;

import finalproject.application.dto.content.ContentTypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
public class TaskRequestDto {

  /**
   * Возвращает @return name название задачи.
   */
  private @Getter String name;

  /**
   * Возвращает @return type тип контента.
   */
  private @Getter String type;

  /**
   * Возвращает @return description описание задачи.
   */
  private @Getter String description;

  /**
   * Возвращает @return UserDto автора задачи.
   */
  private @Getter int authorId;

  /**
   * Возвращает @return UserDto исполнителя задачи.
   */
  private @Getter int contentMakerId;

  /**
   * Возвращает @return LocalDateTime дату выполнения задачи.
   */
  private @Getter String dateExpired;

}
