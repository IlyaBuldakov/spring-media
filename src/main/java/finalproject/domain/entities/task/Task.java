package finalproject.domain.entities.task;


import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.user.User;
import finalproject.utils.Validators;
import io.vavr.control.Either;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Возвращает @return id идентификатор задачи.
 */
@Entity
@Table(name = "tasks")
public class Task implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  int id;

  /** Название задачи.
   *
   * @return name название задачи.
   */
  @Column
  @Getter
  @Setter
  private String name;

  /**Тип контента.
   *
   * @return type тип контента.
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

  /** Автор / инициатор / менеджер задачи.
   *
   * @return author автор задачи
   */
  @Column
  @Getter
  @Setter
  private User author;

  /** Исполнитель задачи / контентмейкер.
   *
   * @return contentMaker исполнитель задачи.
   */
  @Column
  @Getter
  @Setter
  private User contentMaker;


  /**Дата создания задачи.
   *
   * @return dateCreated LocalDateTime дату создания задачи.
   */
  @Column
  @Getter
  @Setter
  private LocalDateTime dateCreated;

  /**Дата выполнения задачи.
   *
   * @return dateExpired - LocalDateTime дату выполнения задачи.
   */
  @Column
  @Getter
  @Setter
  private LocalDateTime dateExpired;



  /**Статус задачи.
   *
   * @return taskStatus статус задачи.
   */
  @Column
  @Getter
  @Setter
  private TaskStatus taskStatus;

  public Task() {}

  /**
   * Конструктор сущности "Задача".
   *
   * @param name наименование
   * @param type тип контента
   * @param description описание
   * @param author автор / инициатор
   * @param contentMaker исполнитель
   * @param stringDate дата завершения задачи
   * @return task задачу
   */
  public static Either<Failure, Task> create(String name, ContentType type,
                                              String description, User author,
                                       User contentMaker, String stringDate) {
    Validators validators = new Validators();
    validators.validateNonNullString(name, "name");
    validators.validateNotNull(type, "contentType");
    validators.validateNonNullString(description, "description");
    validators.validateNotNull(contentMaker, "contentMaker");
    Task task = new Task();
    task.dateExpired = validators.validateDateTime(stringDate);


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
