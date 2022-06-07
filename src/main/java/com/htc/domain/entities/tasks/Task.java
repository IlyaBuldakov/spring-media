package com.htc.domain.entities.tasks;

import com.htc.domain.entities.comments.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.user.User;
import java.time.LocalDateTime;
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

  /**
   * Создаёт тестовую задачу с указанным идентификатором.
   *
   * @param id Идентификатор задачи.
   * @return Задача.
   */
  public static Task createTestTask(int id) {
    return new Task(
            id,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    );
  }

  public static Task createTestTask() {
    var id = new Random().nextInt(Integer.MAX_VALUE);
    return createTestTask(id);
  }
}
