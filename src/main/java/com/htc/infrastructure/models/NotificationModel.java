package com.htc.infrastructure.models;

import com.htc.domain.entities.Notification;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Модель уведомлений для СУБД.
 */
@Entity
@Table(name = "Notifications")
public class NotificationModel implements Notification {

  /**
   * Идентификатор уведомления.
   */
  @javax.persistence.Id
  @GeneratedValue
  private Integer id;

  /**
   * Тип уведомления.
   */
  @Enumerated(EnumType.STRING)
  NotificationType notificationType;

  /**
   * Дата создания уведомления.
   */
  LocalDateTime dateCreateNotification;

  /**
   * Сообщение уведомления.
   */
  String notificationMessage;

  /**
   * Идентификатор пользователя для которого уведомление.
   */
  int userId;

  /**
   * Идентификатор задачи для которой уведомление.
   */
  int taskId;

  @Override
  public Id getId() {
    return Id.create(this.id).get();
  }

  @Override
  public NotificationType getNotificationType() {
    return notificationType;
  }

  @Override
  public LocalDateTime getDateCreateNotification() {
    return dateCreateNotification;
  }

  @Override
  public String getNotificationMessage() {
    return notificationMessage;
  }

  @Override
  public int getUserId() {
    return userId;
  }

  @Override
  public int getTaskId() {
    return taskId;
  }

  /**
   * Создает экземпляр класса {@link NotificationModel}.
   */
  protected NotificationModel() {}

  /**
   * Создает экземпляр класса {@link NotificationModel}.
   *
   * @param notificationType Тип уведомления.
   * @param dateCreateNotification Дата создания уведомления.
   * @param notificationMessage Сообщение уведомления.
   * @param userId Идентификатор пользователя для которого уведомление.
   * @param taskId Идентификатор задачи от которой уведомление.
   */
  public NotificationModel(
      NotificationType notificationType,
      LocalDateTime dateCreateNotification,
      String notificationMessage,
      int userId,
      int taskId) {
    this(Id.create(0).get(), notificationType, dateCreateNotification, notificationMessage, userId, taskId);
  }

  /**
   * Создает экземпляр класса {@link NotificationModel}.
   *
   * @param id Идентификатор уведомления.
   * @param notificationType Тип уведомления.
   * @param dateCreateNotification Дата создания уведомления.
   * @param notificationMessage Сообщение уведомления.
   * @param userId Иденификатор пользователя для которого уведомление.
   * @param taskId Идентификатор задачи, которой принадлежит уведомление.
   */
  public NotificationModel(
      Id id,
      NotificationType notificationType,
      LocalDateTime dateCreateNotification,
      String notificationMessage,
      int userId,
      int taskId) {
    this.id = id.getValue();
    this.notificationType = notificationType;
    this.dateCreateNotification = dateCreateNotification;
    this.notificationMessage = notificationMessage;
    this.userId = userId;
    this.taskId = taskId;
  }
}