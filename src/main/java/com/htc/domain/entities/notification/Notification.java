package com.htc.domain.entities.notification;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;

/**
 * Уведомление.
 */
public interface Notification {
  /**
   * Идентификатор уведомления.
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * {@link Type Тип} контента уведомления.
   *
   * @return type тип контента
   */
  Type getType();

  /**
   * Дата уведомления.
   *
   * @return dateNotification дата уведомления
   */
  DateCreated getDateNotification();

  /**
   * Текст уведомления.
   *
   * @return message текст уведомления
   */
  String getMessage();

  /**
   * Идентификатор {@link User пользователя}.
   *
   * @return id идентификатор пользователя
   */
  User getUser();

  /**
   * Идентификатор {@link Task задачи}.
   *
   * @return id идентификатор задачи
   */
  Task getTask();
}
