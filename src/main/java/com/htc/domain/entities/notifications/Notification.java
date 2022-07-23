package com.htc.domain.entities.notifications;

import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.time.LocalDateTime;

/**
 * Уведомление.
 */
public interface Notification {
  /**
   * Индентификатор уведомления.
   *
   * @return Индентификатор уведомления.
   */
  Id getId();

  /**
   * Тип уведомления.
   *
   * @return Тип уведомления.
   */
  Type getType();

  /**
   * Дата получения уведомления.
   *
   * @return Дата плучения уведомления.
   */
  LocalDateTime getDate();

  /**
   * Сообщение уведомления.
   *
   * @return Сообщение уведомления.
   */
  Message getMessage();

  /**
   * Получатель уведомления.
   *
   * @return Пользователь - получатель уведомления.
   */
  User getUser();

  /**
   * Задача связанная с уведомлнием.
   *
   * @return Задача связанная с уведомлнием.
   */
  Task getTask();

  /**
   * Тип уведомления.
   */
  enum Type {
    VIDEO,
    AUDIO,
    PHOTO,
    COMMENT,
    CONTENT
  }

  /**
   * Сообщение уведомления.
   */
  class Message extends Attribute<String> {
    /**
     * Создаёт сообщение уведомления.
     *
     * @param value Входные данные.
     * @return Сообщение уведомления.
     */
    public static Either<InvalidValue, Notification.Message> create(String value) {
      var message = new Notification.Message(value);
      return Either.right(message);
    }

    private Message(String value) {
      super(value);
    }
  }
}
