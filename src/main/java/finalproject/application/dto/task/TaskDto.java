package finalproject.application.dto.task;

import finalproject.application.dto.comment.CommentDto;
import finalproject.application.dto.content.ContentDto;
import finalproject.application.dto.content.ContentTypeDto;
import finalproject.application.dto.file.FileDto;
import finalproject.application.dto.user.UserDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Задача.
 */

@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends TaskListDto {

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
   * Возвращает @return LocalDateTime дату создания задачи.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   * Возвращает @return ContentDto[] contents приложенный контент.
   */
  private @Getter ContentDto[] contents;

  /**
   * Возвращает @return CommentDto[] comments комментарии к задаче.
   */
  private @Getter CommentDto[] comments;

  /**
   * DTO задачи.
   *
   * @param id Идентификатор задачи
   * @param name Имя
   * @param type Тип контента
   * @param executor Исполнитель / контент/менеджер
   * @param dateExpired Дата выполнения
   * @param status Статус задачи
   * @param description Описание задачи
   * @param files Файлы задачи
   * @param author Инициатор / менеджер задачи
   * @param dateCreated Дата создания задачи
   * @param contents Контент
   * @param comments Комментарии к задаче
   */
  public TaskDto(int id, String name, ContentTypeDto type, UserDto executor,
                 LocalDateTime dateExpired,
                 TaskStatusDto status, String description, FileDto[] files, UserDto author,
                 LocalDateTime dateCreated,
                 ContentDto[] contents, CommentDto[] comments) {
    super(id, name, type, executor, dateExpired, status);
    this.description = description;
    this.files = files;
    this.author = author;
    this.dateCreated = dateCreated;
    this.contents = contents;
    this.comments = comments;
  }
}
