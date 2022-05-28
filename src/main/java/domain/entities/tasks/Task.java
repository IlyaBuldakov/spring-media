package domain.entities.tasks;

import domain.entities.comments.Comment;
import domain.entities.content.Content;
import domain.entities.content.ContentType;
import domain.entities.files.File;
import domain.entities.user.User;
import java.time.LocalDateTime;
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
  private @Getter int id;
  /**
   * Заголовок задачи.
   *
   * @return заголовок задачи.
   */
  private @Getter String name;
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
  private @Getter String description;
  /**
   * Приложеные файлы..
   *
   * @return Файлы задачи.
   */
  private @Getter File[] files;
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
  private @Getter Content[] contents;
  /**
   * Коментарии задачи.
   *
   * @return Коментарии задачи.
   */
  private @Getter Comment[] comments;
  /**
   * Статус задачи.
   *
   * @return Статус задачи.
   */
  private @Getter TaskStatus status;

}
