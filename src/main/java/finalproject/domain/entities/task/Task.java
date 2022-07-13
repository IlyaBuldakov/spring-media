package finalproject.domain.entities.task;


import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import finalproject.utils.Validators;
import io.vavr.control.Either;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

public class Task {
  /**
   * Возвращает @return id идентификатор задачи.
   */

  @Id
  @GeneratedValue
  @Getter
  @Setter
  private  int id;

  /**
   * Возвращает @return name название задачи.
   */
  @Getter
  @Setter
  private String name;

  /**
   * Возвращает @return type тип контента.
   */
  @Getter
  @Setter
  private ContentType type;

  /**
   * Возвращает @return description описание задачи.
   */
  @Getter
  @Setter
  private String description;

  /**
   * Возвращает @return автора задачи.
   */
  private @Getter User author;

  /**
   * Возвращает @return исполнителя задачи.
   */
  @Getter
  @Setter
  private User contentMaker;

  /**
   * Возвращает @return менеджера задачи.
   */
  @Getter
  @Setter
  private User manager;

  /**
   * Возвращает @return LocalDateTime дату создания задачи.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   * Возвращает @return LocalDateTime дату выполнения задачи.
   */
  @Getter
  @Setter
  private LocalDateTime dateExpired;



  /**
   * Возвращает @return TaskStatusDto status статус задачи.
   */
  @Getter
  @Setter
  private TaskStatus taskStatus;

  public Task() {}

  public static Either<Failure, Task> create (String name, ContentType type, String description, User author,
                                       User contentMaker, String stingDate) {
    Task task = new Task();
    Validators validators = new Validators();
    validators.validateNonNullString(name, "name");
    validators.validateNotNull(type, "contentType");
    validators.validateNonNullString(description, "description");
    validators.validateNotNull(contentMaker, "contentMaker");
    if(validators.validateDateTime(stingDate)) {
      task.dateExpired = LocalDateTime.parse(stingDate);
      validators.validateDateTime(task.dateExpired);
    };
    if (validators.problems.size() == 0) {
      task.name = name;
      task.type = type;
      task.description = description;
      task.author = author;
      task.contentMaker = contentMaker;
      task.taskStatus = TaskStatus.INWORK;

      return Either.right(task);
    }

    return Either.left(new Failure(Failure.Messages.INVALID_VALUES, validators.problems));
  }




}
