package com.htc.application.dto.notification;

import com.htc.application.dto.task.TaskCardResponse;
import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.notifications.Notification;
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
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;
  /**
   * Тип уведомления.
   *
   * @return Тип уведомления.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Notification.Type type;
  /**
   * Дата получения уведомления.
   *
   * @return Дата плучения уведомления.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime date;
  /**
   * Сообщение уведомления.
   *
   * @return Сообщение уведомления.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String message;
  /**
   * Получатель уведомления.
   *
   * @return Пользователь - получатель уведомления.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserShortResponse user;
  /**
   * Задача. Задача связанная с уведомлнием.
   *
   * @return Задача.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter TaskCardResponse task;

  /**
   * Создаёт экземпляр класса {@link NotificationDto}.
   *
   * @param notification Уведомление.
   */
  public NotificationDto(Notification notification) {
    this.id = notification.getId().getValue();
    this.type = notification.getType();
    this.date = notification.getDate();
    this.message = notification.getMessage().getValue();
    this.user = new UserShortResponse(notification.getUser());
    this.task = new TaskCardResponse(notification.getTask());
  }
}
