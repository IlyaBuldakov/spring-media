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
   * Получить идентификатор уведомления.
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Получить {@link Type тип} контента уведомления.
   *
   * @return type тип контента
   */
  Type getType();

  /**
   * Получить дату уведомления.
   *
   * @return dateNotification дата уведомления
   */
  DateCreated getDateNotification();

  /**
   * Получить текст уведомления.
   *
   * @return message текст уведомления
   */
  String getMessage();

  /**
   * Получить идентификатор {@link User пользователя}.
   *
   * @return id идентификатор пользователя
   */
  User getUser();

  /**
   * Получить идентификатор {@link Task задачи}.
   *
   * @return id идентификатор задачи
   */
  Task getTask();
}
