package com.htc.infrastructure.models;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.notifications.Notification;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Модель уведомлений для СУБД.
 */
@Entity
@Table(name = "Notifications")
@AllArgsConstructor
public class NotificationModel implements Notification {

  /**
   * Идентификатор уведомления.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "notification_id")
  private Integer notificationId;

  @Override
  public Id getId() {
    return Id.create(this.notificationId).get();
  }

  /**
   * Тип уведомления.
   */
  @Enumerated(EnumType.STRING)
  private @Getter Type type;

  /**
   * Дат создания уведомления.
   */
  private @Getter LocalDateTime date;

  /**
   * Сообщение уведомления.
   */
  private String message;

  @Override
  public Message getMessage() {
    return Notification.Message.create(this.message).get();
  }





  /**
   * Пользователь чье действие отображается в уведомлении.
   */
  @ManyToOne
  @JoinColumn(name = "parent_user_id", referencedColumnName = "user_id", nullable = false)
  private UserModel parentUser;

  @Override
  public User getUser() {
    return parentUser;
  }

  /**
   * Задача изменение которой отображается в уведомлении.
   */
  @ManyToOne
  @JoinColumn(name = "parent_task_id", referencedColumnName = "task_id", nullable = false)
  private TaskModel parentTask;

  @Override
  public Task getTask() {
    return parentTask;
  }

  protected NotificationModel() {
  }

  /**
   * Создает модель цведомления.
   *
   * @param id Индентификатор уведомления.
   * @param type Тип уведомления.
   * @param date Дата создания уведомления.
   * @param message Тескт уведомления.
   * @param user Пользователь связанный с уведомлением.
   * @param task Задача связанный с уведомлением.
   */
  public NotificationModel(Id id, Notification.Type type, LocalDateTime date, Message message,
                           UserModel user, TaskModel task) {
    this.notificationId = id.getValue();
    this.type = type;
    this.date = date;
    this.message = message.getValue();
    this.parentUser = user;
    this.parentTask = task;
  }
}
