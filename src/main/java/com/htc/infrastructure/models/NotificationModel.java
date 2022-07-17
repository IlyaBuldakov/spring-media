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

  @Enumerated(EnumType.STRING)
  NotificationType notificationType;
  LocalDateTime dateCreateNotification;
  String notificationMessage;
  int userId;
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

  protected NotificationModel() {}

  public NotificationModel(
      NotificationType notificationType,
      LocalDateTime dateCreateNotification,
      String notificationMessage,
      int userId,
      int taskId) {
    this(Id.create(0).get(), notificationType, dateCreateNotification, notificationMessage, userId, taskId);
  }

  /**
   * Конструктор для получения сущности уведомлений из базы данных.
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