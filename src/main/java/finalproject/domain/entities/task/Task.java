package finalproject.domain.entities.task;


import com.fasterxml.jackson.annotation.JsonIgnore;
import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.failures.BadRequest;
import finalproject.domain.entities.failures.Failure;
import finalproject.domain.entities.failures.Messages;
import finalproject.domain.entities.user.User;
import finalproject.utils.validators.Validators;
import io.vavr.control.Either;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

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

  @JsonIgnore
  @Getter
  @Setter
  @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "authorId")
  private User author;

  /** Исполнитель задачи / контентмейкер.
   *
   * @return contentMaker исполнитель задачи.
   */
  @JsonIgnore
  @Getter
  @Setter
  @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "contentMakerId")
  private User contentMaker;

  @JsonIgnore
  @Getter
  @Setter
  @OneToMany(mappedBy = "task")
  private List<Content> allTaskContent;


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
   * @param author id автора / инициатора
   * @param contentMaker id исполнителя
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


    if (validators.getProblems().size() == 0) {
      task.name = name;
      task.type = type;
      task.description = description;
      task.author = author;
      task.contentMaker = contentMaker;


      return Either.right(task);
    }

    return Either.left(new BadRequest(Messages.INVALID_VALUES, validators.getProblems()));
  }




}
