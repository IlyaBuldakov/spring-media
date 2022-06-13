package com.htc.domain.entities.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Задача.
 */
public class Task {
  /**
   *  Идентификатор задачи.
   *
   * @return id Идентификатор задачи.
   */
  private @Getter int id;

  /**
   *  Название задачи.
   *
   * @return name Название задачи.
   */
  private @Getter String name;

  /**
   *  Тип контента.
   *
   * @return contentType Тип контента, см. {@Link ContentType}
   */
  private @Getter ContentType contentType;

  /**
   *  Описание задачи.
   *
   * @return description Описание задачи.
   */
  private @Getter String description;

  /**
   *  Приложенные к задаче файлы.
   *
   * @return file Приложенные к задаче файлы, см. {@Link File}
   */
  private @Getter File file;

  /**
   *  Автор задачи.
   *
   * @return author Автор задачи, см. {@Link User}
   */
  private @Getter User author;

  /**
   *  Исполнитель задачи.
   *
   * @return executor Исполнитель задачи, см. {@Link User}
   */
  private @Getter User executor;

  /**
   *  Дата создания задачи.
   *
   * @return dateCreated Дата создания задачи.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   *  Дата окончания задачи.
   *
   * @return dateExpired Дата окончания задачи.
   */
  private @Getter LocalDateTime dateExpired;

  /**
   *  Приложенный контент.
   *
   * @return contents Приложенный контент, см. {@Link Content}
   */
  private @Getter Content contents;

  /**
   *  Комментарий в задаче.
   *
   * @return comment Комментарий в задаче, см. {@Link Comment}
   */
  private @Getter Comment comment;

  /**
   *  Статус задачи.
   *
   * @return status Статус задачи, см. {@Link TaskStatus}
   */
  private @Getter TaskStatus status;

  /**
   * Создает задачу.
   *
   * @param id Идентификатор задачи.
   * @param name Имя задачи.
   * @param contentType Тип контента.
   * @param description Описание задачи.
   * @param file Файл задачи.
   * @param author Автор задачи.
   * @param executor Исполнитель задачи.
   * @param dateCreated Дата создания задачи.
   * @param dateExpired Дата окончания задачи.
   * @param contents Контент задачи.
   * @param comment Комментарий задачи.
   * @param status Статус задачи.
   * @return Задача.
   */
  public static Either<Failure, Task> create(
      int id, String name, ContentType contentType, String description, File file, User author,
      User executor, LocalDateTime dateCreated, LocalDateTime dateExpired, Content contents,
      Comment comment, TaskStatus status) {

    var task = new Task();
    task.id = id;
    task.name = name;
    task.contentType = contentType;
    task.description = description;
    task.file = file;
    task.author = author;
    task.executor = executor;
    task.dateCreated = dateCreated;
    task.dateExpired = dateExpired;
    task.contents = contents;
    task.comment = comment;
    task.status = status;
    return Either.right(task);
  }
}
