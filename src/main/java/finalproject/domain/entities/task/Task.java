package finalproject.domain.entities.task;


import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import finalproject.utils.Validators;
import io.vavr.control.Either;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
  /**
   * Возвращает @return id идентификатор задачи.
   */


  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  int id;

  /**
   * Возвращает @return name название задачи.
   */
  @Column
  @Getter
  @Setter
  private String name;

  /**
   * Возвращает @return type тип контента.
   */
  @Column
  @Getter
  @Setter
  private ContentType type;

  /**
   * Возвращает @return description описание задачи.
   */
  @Column
  @Getter
  @Setter
  private String description;

  /**
   * Возвращает @return автора задачи.
   */
  @Column
  @Getter
  @Setter
  private User author;

  /**
   * Возвращает @return исполнителя задачи.
   */
  @Column
  @Getter
  @Setter
  private User contentMaker;

  /**
   * Возвращает @return менеджера задачи.
   */
  @Column
  @Getter
  @Setter
  private User manager;

  /**
   * Возвращает @return LocalDateTime дату создания задачи.
   */
  @Column
  @Getter
  @Setter
  private LocalDateTime dateCreated;

  /**
   * Возвращает @return LocalDateTime дату выполнения задачи.
   */
  @Column
  @Getter
  @Setter
  private LocalDateTime dateExpired;



  /**
   * Возвращает @return TaskStatusDto status статус задачи.
   */
  @Column
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
    task.dateExpired = validators.validateDateTime(stingDate);


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
