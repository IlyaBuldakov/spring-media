package com.htc.domain.entities.tasks;

import com.htc.application.dto.file.FileDto;
import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Задачи.
 */
@AllArgsConstructor
public class Task {
  /**
   * Индентификатор задачи.
   *
   * @return Индентификатор задачи.
   */
  private @Getter Id id;
  /**
   * Заголовок задачи.
   *
   * @return заголовок задачи.
   */
  private @Getter Name name;
  /**
   * Требуемный тип контента.
   *
   * @return Тип контента.
   */
  private @Getter ContentType contentType;
  /**
   * Описание задачи.
   *
   * @return Описание задачи.
   */
  private @Getter Description description;
  /**
   * Приложеные файлы..
   *
   * @return Файлы задачи.
   */
  private @Getter Collection<File> files;
  /**
   * Автор задачи.
   *
   * @return Позьзователя - автора задачи.
   */
  private @Getter User author;
  /**
   * Исполнитель задачи.
   *
   * @return Позьзователя - исполнителя задачи.
   */
  private @Getter User executor;
  /**
   * Дата создания задачи.
   *
   * @return Дату создания задачи.
   */
  private @Getter LocalDateTime dateCreated;
  /**
   * Срок выполнения задачи.
   *
   * @return Срок выполнения задачи.
   */
  private @Getter LocalDateTime dateExpired;
  /**
   * Контент - результат выполнения задачи.
   *
   * @return Контент.
   */
  private @Getter Collection<Content> contents;
  /**
   * Коментарии задачи.
   *
   * @return Коментарии задачи.
   */
  private @Getter Collection<Comment> comments;
  /**
   * Статус задачи.
   *
   * @return Статус задачи.
   */
  private @Getter TaskStatus status;


  private Task() {
  }


  /**
   * Создает задачу.
   *
   * @param id Индентификатор задачи.
   * @param name Название задачи.
   * @param contentType Тип контента.
   * @param description Описание задачи.
   * @param files Файлы задачи.
   * @param author Пользователь - автор задачи.
   * @param executor Пользователь - исполнитель задачи.
   * @param dateCreated Дата саздания задачи.
   * @param dateExpired Срок выполнения задачи.
   * @param contents Медиаконтент.
   * @param comments Комментарии.
   * @param status Статус задачи.
   * @return Задача, либо ошибку создания задачи.
   */
  public static Either<Failure, Task> create(
          Id id,
          Name name,
          ContentType contentType,
          Description description,
          Collection<File> files,
          User author,
          User executor,
          LocalDateTime dateCreated,
          LocalDateTime dateExpired,
          Collection<Content> contents,
          Collection<Comment> comments,
          TaskStatus status) {

    var task = new Task();
    task.id = id;
    task.name = name;
    task.contentType = contentType;
    task.description = description;
    task.files = files;
    task.author = author;
    task.executor = executor;
    task.dateCreated = dateCreated;
    task.dateExpired = dateExpired;
    task.contents = contents;
    task.comments = comments;
    task.status = status;
    return Either.right(task);
  }

  /**
   * Название задачи.
   */
  public static final class Name extends Attribute<String> {

    /**
     * Проверяет входные данные на корректность и создаёт название задачи.
     * Название задачи не должно быть пустой строкой и не должно быть длиннее 32 символов
     *
     * @param value Входные данные.
     * @return Название задачи или ошибка.
     */
    public static Either<InvalidValue, Task.Name> create(String value) {
      if (value.length() == 0 || value.length() > 32) {
        return Either.left(InvalidValue.INVALID_TASK_NAME);
      }

      var taskName = new Task.Name(value);
      return Either.right(taskName);
    }
    
    private Name(String value) {
      super(value);
    }

  }

  /**
   * Описание задачи.
   */
  public static final class Description extends Attribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт описание задачи.
     * Описание задачи не должно быть пустой строкой.
     *
     * @param value Входные данные.
     * @return Описание задачи или ошибка.
     */
    public static Either<InvalidValue, Task.Description> create(String value) {
      if (value.length() == 0) {
        return Either.left(InvalidValue.INVALID_TASK_DESCRIPTION);
      }

      var taskDescription = new Task.Description(value);
      return Either.right(taskDescription);
    }

    private Description(String value) {
      super(value);
    }
  }

  /**
   * Статус задачи.
   */
  public enum TaskStatus {
    /**
     * В работе.
     */
    IN_WORK("В работе", 1),
    /**
     * Ожидает согласование.
     */
    FEEDBACK("Ожидает согласование", 2),
    /**
     * Выполнено.
     */
    APPROVED("Выполнено", 3);

    /**
     * Идентификатор статуса.
     *
     * @return id Идентификатор статуса.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter int id;

    /**
     * Название статуса.
     *
     * @return Название статуса задачи.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter String name;

    TaskStatus(String name, int id) {
      this.name = name;
      this.id = id;
    }
  }


}
