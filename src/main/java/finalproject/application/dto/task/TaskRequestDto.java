package finalproject.application.dto.task;

import finalproject.application.dto.content.ContentTypeDto;
import finalproject.application.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class TaskRequestDto {

  /**
   * Возвращает @return name название задачи.
   */
  private @Getter String name;

  /**
   * Возвращает @return type тип контента.
   */
  private @Getter ContentTypeDto type;

  /**
   * Возвращает @return description описание задачи.
   */
  private @Getter String description;

  /**
   * Возвращает @return UserDto автора задачи.
   */
  private @Getter UserDto author;

  /**
   * Возвращает @return UserDto исполнителя задачи.
   */
  private @Getter UserDto executor;

  /**
   * Возвращает @return LocalDateTime дату выполнения задачи.
   */
  private @Getter LocalDateTime dateExpired;

}
