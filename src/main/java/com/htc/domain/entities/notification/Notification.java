package com.htc.domain.entities.notification;

import com.htc.domain.entities.content.Type;
import lombok.Getter;

/**
 * Уведомление.
 */
public class Notification {
  /**
   * Идентификатор уведомления.
   *
   * @return id идентификатор
   */
  private @Getter int id;

  /**
   * {@link Type Тип} контента уведомления.
   *
   * @return type тип контента
   */
  private @Getter Type type;

  /**
   * Дата уведомления.
   *
   * @return dateNotification дата уведомления
   */
  private @Getter String dateNotification;

  /**
   * Текст уведомления.
   *
   * @return message текст уведомления
   */
  private @Getter String message;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User пользователя}.
   *
   * @return id идентификатор пользователя
   */
  private @Getter int userId;

  /**
   * Идентификатор {@link com.htc.domain.entities.task.Task задачи}.
   *
   * @return id идентификатор задачи
   */
  private @Getter int taskId;
}
