package com.htc.infrastructure.models;

import com.htc.domain.entities.Entity;
import com.htc.domain.entities.Notification;
import com.htc.domain.entities.attributes.Id;
import com.htc.infrastructure.exception.InvalidDataException;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

/**
 * Модель уведомлений для СУБД.
 */
@javax.persistence.Entity
@Table(name = "Notifications")
@AllArgsConstructor
public class NotificationModel implements Entity.Model<Notification> {

  /**
   * Идентификатор уведомления.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id")
  private Integer notificationId;

  /**
   * Тип уведомления.
   */
  @NotNull
  @Enumerated(EnumType.STRING)
  private Notification.Type type;

  /**
   * Дат создания уведомления.
   */
  @NotNull
  private LocalDateTime date;

  /**
   * Сообщение уведомления.
   */
  @NotNull
  private String message;

  /**
   * Пользователь чье действие отображается в уведомлении.
   */
  @NotNull
  @ManyToOne
  private UserModel parentUser;

  /**
   * Задача изменение которой отображается в уведомлении.
   */
  @NotNull
  @ManyToOne
  private TaskModel parentTask;

  protected NotificationModel() {
  }

  @Override
  public Notification toEntity() throws InvalidDataException {
    final var id = Id
        .create(this.notificationId)
        .getOrElseThrow(InvalidDataException::new);

    final var message = Notification.Message
        .create(this.message)
        .getOrElseThrow(InvalidDataException::new);

    final var parentUser = this.parentUser.toEntity();
    final var parentTask = this.parentTask.toEntity();

    return new Notification(id, type, date, message, parentUser, parentTask);
  }

  public NotificationModel(
      Notification.Type type,
      LocalDateTime date,
      String message,
      UserModel parentUser,
      TaskModel parentTask) {
    this(0, type, date, message, parentUser, parentTask);
  }
}
