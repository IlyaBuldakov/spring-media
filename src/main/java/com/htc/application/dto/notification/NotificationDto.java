package com.htc.application.dto.notification;

import com.htc.application.dto.task.TaskCardResponse;
import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.Entity;
import com.htc.domain.entities.Notification;
import java.time.LocalDateTime;

/**
 * Представление сущности уведомления.
 *
 * @param id Индентификатор уведомления.
 * @param type Тип уведомления.
 * @param date Дата получения уведомления.
 * @param message Сообщение уведомления.
 * @param user Получатель уведомления.
 * @param task Задача. Задача связанная с уведомлнием.
 */
public record NotificationDto(
    int id,
    Notification.Type type,
    LocalDateTime date,
    String message,
    UserShortResponse user,
    TaskCardResponse task)
    implements Entity.View<NotificationDto, Notification> {
  @Override
  public NotificationDto fromEntity(Notification notification) {
    return new NotificationDto(notification);
  }

  /**
   * Создаёт экземпляр класса {@link NotificationDto}.
   *
   * @param notification Уведомление.
   */
  public NotificationDto(Notification notification) {
    this(notification.id().getValue(),
        notification.type(),
        notification.dateCreated(),
        notification.message().getValue(),
        new UserShortResponse(notification.user()),
        new TaskCardResponse(notification.task()));
  }
}
