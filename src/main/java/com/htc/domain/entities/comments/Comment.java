package com.htc.domain.entities.comments;

import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Комментарий.
 */
@AllArgsConstructor
public class Comment {
  /**
   * Идентификатор Комментария.
   *
   * @return  Идентификатор комментария.
   */
  private @Getter int id;

  /**
   * Дата создания комментария.
   *
   * @return  дату создания комментария.
   */
  private @Getter LocalDateTime date;

  /**
   * Автор комментария.
   *
   * @return user - автора комментария.
   */
  private @Getter User user;

  /**
   * Задача связанная с комментарием.
   *
   * @return task задача.
   */
  private @Getter Task task;
  /**
   * Текст комментария.
   *
   * @return текст комментария.
   */
  private @Getter String message;

  /**
   * Создаёт тестовый комментарий.
   *
   * @return Комментарий.
   */
  public static Comment createTestComment(int id) {
    return new Comment(
            id,
            null,
            null,
            null,
            null
    );
  }

  public static Comment createTestContent() {
    var id = new Random().nextInt(Integer.MAX_VALUE);
    return createTestComment(id);
  }
}
