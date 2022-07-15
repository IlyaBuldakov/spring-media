package com.htc.domain.entities.tasks;

import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.Getter;

/**
 * Задача.
 */
public interface Task {
  /**
   * Индентификатор задачи.
   *
   * @return Индентификатор задачи.
   */
  Id getId();

  /**
   * Заголовок задачи.
   *
   * @return заголовок задачи.
   */
  Name getName();

  /**
   * Требуемный тип контента.
   *
   * @return Тип контента.
   */
  ContentType getContentType();

  /**
   * Описание задачи.
   *
   * @return Описание задачи.
   */
  Description getDescription();

  /**
   * Приложеные файлы..
   *
   * @return Файлы задачи.
   */
  Collection<File> getFiles();

  /**
   * Автор задачи.
   *
   * @return Позьзователя - автора задачи.
   */
  User getAuthor();

  /**
   * Исполнитель задачи.
   *
   * @return Позьзователя - исполнителя задачи.
   */
  User getExecutor();

  /**
   * Дата создания задачи.
   *
   * @return Дату создания задачи.
   */
  LocalDateTime getDateCreated();

  /**
   * Срок выполнения задачи.
   *
   * @return Срок выполнения задачи.
   */
  LocalDateTime getDateExpired();

  /**
   * Контент - результат выполнения задачи.
   *
   * @return Контент.
   */

  Collection<Content> getContents();

  /**
   * Коментарии задачи.
   *
   * @return Коментарии задачи.
   */
  Collection<Comment> getComments();

  /**
   * Статус задачи.
   *
   * @return Статус задачи.
   */
  TaskStatus getStatus();

  /**
   * Название задачи.
   */
  final class Name extends Attribute<String> {

    /**
     * Проверяет входные данные на корректность и создаёт название задачи.
     * Название задачи не должно быть пустой строкой и не должно быть длиннее 32 символов
     *
     * @param value Входные данные.
     * @return Название задачи или ошибка.
     */
    public static Either<InvalidValue, Task.Name> create(String value) {
      if (value.length() == 0 || value.length() > 255) {
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
  final class Description extends Attribute<String> {
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
  enum TaskStatus {
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
