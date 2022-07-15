package com.htc.application.dto.notification;

import com.htc.domain.entities.Notification;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности уведомления (ответ на запрос).
 */
public class NotificationResponse {
  /**
   * Идентификатор уведомления.
   *
   * @return id Идентификатор уведомления.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Тип уведомления, см. {@link Notification.NotificationType}.
   *
   * @return notificationType.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Notification.NotificationType notificationType;

  /**
   * Дата создания уведомления.
   *
   * @return dateCreateNotification.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateCreateNotification;

  /**
   * Сообщение уведомления.
   *
   * @return notificationMessage.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String notificationMessage;

  /**
   * Пользователь для которого уведомление.
   *
   * @return userId.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter
  int userId;

  /**
   * Задача от которой уведомление.
   *
   * @return taskId.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int taskId;

  /**
   * Создаёт экземпляр класса {@link NotificationResponse}.
   *
   * @param notification Сущность уведомления.
   */
  public NotificationResponse(Notification notification) {
    this.id = notification.getId().getValue();
    this.notificationType = notification.getNotificationType();
    this.dateCreateNotification = notification.getDateCreateNotification();
    this.notificationMessage = notification.getNotificationMessage();
    this.userId = notification.getUserId();
    this.taskId = notification.getTaskId();
  }
}
