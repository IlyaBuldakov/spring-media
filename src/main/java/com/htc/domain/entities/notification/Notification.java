package com.htc.domain.entities.notification;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
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
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Id id;

  /**
   * {@link Type Тип} контента уведомления.
   *
   * @return type тип контента
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Type type;

  /**
   * Дата уведомления.
   *
   * @return dateNotification дата уведомления
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter DateCreated dateNotification;

  /**
   * Текст уведомления.
   *
   * @return message текст уведомления
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String message;

  /**
   * Идентификатор {@link User пользователя}.
   *
   * @return id идентификатор пользователя
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter User user;

  /**
   * Идентификатор {@link Task задачи}.
   *
   * @return id идентификатор задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Task task;
}
