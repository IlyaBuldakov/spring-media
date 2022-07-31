package finalproject.application.dto.task;

import finalproject.application.dto.comment.CommentDto;
import finalproject.application.dto.content.ContentDto;
import finalproject.application.dto.file.FileDto;
import finalproject.application.dto.user.UserDto;
import finalproject.domain.entities.task.Task;
import lombok.Getter;


/**
 * Задача.
 */


public class TaskDto extends TaskListDto {

  /**
   * Возвращает @return description описание задачи.
   */
  private final @Getter String description;

  /**
   * Возвращает @return FileDto[] файлы задачи.
   */
  private final @Getter FileDto[] files;

  /**
   * Возвращает @return UserDto автора задачи.
   */
  private final @Getter UserDto author;

  /**
   * Возвращает @return LocalDateTime дату создания задачи.
   */
  private final @Getter String dateCreated;

  /**
   * Возвращает @return ContentDto[] contents приложенный контент.
   */
  private final @Getter ContentDto[] contents;

  /**
   * Возвращает @return CommentDto[] comments комментарии к задаче.
   */
  private @Getter CommentDto[] comments;

  /**
   * DTO задачи.
   *
   * @param task задача
   */
  public TaskDto(Task task) {
    super(task);
    if (task.getAuthor() != null) {this.author = new UserDto(task.getAuthor());}
    else { this.author = null;}
    this.description = task.getDescription();
    this.dateCreated = task.getDateCreated().toString();
    this.contents = task.getAllTaskContent().stream().map(ContentDto::new).toList().toArray(new ContentDto[0]);
    this.files = task.getAllTaskFiles().stream().map(FileDto::new).toList().toArray(new FileDto[0]);


  }
}
