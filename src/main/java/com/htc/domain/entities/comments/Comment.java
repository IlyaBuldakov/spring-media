package com.htc.domain.entities.comments;

import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.time.LocalDateTime;

/**
 * Комментарий.
 */
public interface Comment {
  /**
   * Идентификатор комментария.
   *
   * @return Идентификатор комментария.
   */
  Id getId();

  /**
   * Дата создания комментария.
   *
   * @return Дата создания комментария.
   */
  LocalDateTime getDate();

  /**
   * Автор комментария.
   *
   * @return Пользователь - автора комментария.
   */
  User getUser();

  /**
   * Задача связанная с комментарием.
   *
   * @return task задача.
   */
  Task getTask();

  /**
   * Текст комментария.
   *
   * @return Текст комментария.
   */
  Message getMessage();

  /**
   * Текст уведомления.
   */
  class Message extends Attribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт сообщение уведомления.
     * Сообщение не должно быть пустой строкой.
     *
     * @param value Входные данные.
     * @return Сообщение уведомления или ошибка.
     */
    public static Either<InvalidValue, Comment.Message> create(String value) {
      if (value.length() == 0) {
        return Either.left(InvalidValue.INVALID_TASK_DESCRIPTION);
      }

      var message = new Comment.Message(value);
      return Either.right(message);
    }

    private Message(String value) {
      super(value);
    }
  }
}
