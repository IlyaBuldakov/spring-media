package com.htc.infrastructure.models.notification;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.notification.Notification;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.infrastructure.models.task.TaskModel;
import com.htc.infrastructure.models.user.UserModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;

/**
 * Представление сущности уведомления в СУБД.
 */
@Entity
@Table(name = "NOTIFICATIONS")
@AllArgsConstructor
public class NotificationModel implements Notification {
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id")
  Long notificationId;

  @Column(name = "type")
  String type;

  @Column(name = "dateNotification")
  String dateNotification;

  @Column(name = "message")
  String message;

  @ManyToOne
  @JoinColumn(name = "userId")
  UserModel user;

  @ManyToOne
  @JoinColumn(name = "taskId")
  TaskModel task;

  @Override
  public Id getId() {
    return Id.create(this.notificationId).get();
  }

  @Override
  public Type getType() {
    return Type.getFromName(this.type);
  }

  @Override
  public DateCreated getDateNotification() {
    return DateCreated.create(this.dateNotification).get();
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public User getUser() {
    return this.user;
  }

  @Override
  public Task getTask() {
    return this.task;
  }

  protected NotificationModel() {}
}
