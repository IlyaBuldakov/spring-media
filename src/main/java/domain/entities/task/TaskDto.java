package domain.entities.task;

import domain.entities.comment.CommentDto;
import domain.entities.content.ContentDto;
import domain.entities.content.ContentTypeDto;
import domain.entities.file.FileDto;
import domain.entities.user.UserDto;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Задача.
 */

@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends TaskBasicDto {

  /**
   * Возвращает @return type тип контента.
   */
  private @Getter ContentTypeDto type;

  /**
   * Возвращает @return description описание задачи.
   */
  private @Getter String description;

  /**
   * Возвращает @return FileDto[] файлы задачи.
   */
  private @Getter FileDto[] files;

  /**
   * Возвращает @return UserDto автора задачи.
   */
  private @Getter UserDto author;

  /**
   * Возвращает @return UserDto исполнителя задачи.
   */
  private @Getter UserDto executor;

  /**
   * Возвращает @return LocalDate дату создания задачи.
   */
  private @Getter LocalDate dateCreated;

  /**
   * Возвращает @return LocalDate дату выполнения задачи.
   */
  private @Getter LocalDate dateExpired;

  /**
   * Возвращает @return ContentDto[] contents приложенный контент.
   */
  private @Getter ContentDto[] contents;

  /**
   * Возвращает @return CommentDto[] comments комментарии к задаче.
   */
  private @Getter CommentDto[] comments;

  /**
   * Возвращает @return TaskStatusDto status статус задачи.
   */
  private @Getter TaskStatusDto status;

}
