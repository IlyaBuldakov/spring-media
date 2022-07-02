package com.htc.application.dto.notification;

import com.htc.domain.entities.notifications.Notification;
import com.htc.domain.entities.notifications.NotificationType;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности уведомления.
 */
public class NotificationDto {
  /**
   * Индентификатор уведомления.
   *
   * @return Индентификатор уведомления.
   */
  private final @Getter int id;
  /**
   * Тип уведомления.
   *
   * @return Тип уведомления.
   */
  private final @Getter NotificationType type;
  /**
   * Дата получения уведомления.
   *
   * @return Дата плучения уведомления.
   */
  private final @Getter LocalDateTime date;
  /**
   * Сообщение уведомления.
   *
   * @return Сообщение уведомления.
   */
  private final @Getter String message;
  /**
   * Получатель уведомления.
   *
   * @return Пользователь - получатель уведомления.
   */
  private final @Getter User user;
  /**
   *  Задача. Задача связанная с уведомлнием.
   *
   * @return Задача.
   */
  private final @Getter Task task;

  /**
   * Создаёт экземпляр класса {@link NotificationDto}.
   *
   * @param notification Уведомление.
   */
  public NotificationDto(Notification notification) {
    this.id = notification.getId();
    this.type = notification.getType();
    this.date = notification.getDate();
    this.message = notification.getMessage();
    this.user = notification.getUser();
    this.task = notification.getTask();
  }
}
