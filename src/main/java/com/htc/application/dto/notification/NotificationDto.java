package com.htc.application.dto.notification;

import com.htc.application.dto.task.TaskCardResponse;
import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.notifications.Notification;
import java.time.LocalDateTime;
import lombok.Getter;

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
public record NotificationDto(int id,
                              Notification.Type type,
                              LocalDateTime date,
                              String message,
                              UserShortResponse user,
                              TaskCardResponse task) {
    /**
   * Создаёт экземпляр класса {@link NotificationDto}.
   *
   * @param notification Уведомление.
   */
  public NotificationDto(Notification notification) {
    this(notification.getId().getValue(),
      notification.getType(),
      notification.getDate(),
      notification.getMessage().getValue(),
      new UserShortResponse(notification.getUser()),
      new TaskCardResponse(notification.getTask()));
  }
}
