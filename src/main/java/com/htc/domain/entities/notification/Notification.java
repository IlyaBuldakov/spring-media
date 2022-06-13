package com.htc.domain.entities.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Уведомления.
 */
public class Notification {

  /**
   * Идентификатор уведомления.
   *
   * @return id Идентификатор уведомления.
   */
  private @Getter int id;

  /**
   * Тип уведомления.
   *
   * @return type Тип уведомления, см. {@Link NotificationType}
   */
  private @Getter NotificationType type;

  /**
   * Дата уведомления.
   *
   * @return date Дата уведомления.
   */
  private @Getter LocalDateTime date;

  /**
   * Сообщение уведомления.
   *
   * @return message Сообщение уведомления.
   */
  private @Getter String message;

  /**
   * Пользователь для которого уведомление.
   *
   * @return user Пользователь для которого уведомление, см. {@Link User}
   */
  private @Getter User user;

  /**
   * Задача от которой уведомление.
   *
   * @return task Задача от которой уведомление, см. {@Link Task}
   */
  private @Getter Task task;

  /**
   * Создаёт задачу.
   *
   * @param id Идентификатор уведомления.
   * @param type Тип уведомления.
   * @param date Дата уведомления.
   * @param message Сообщение уведомления.
   * @param user Пользователь для которого уведомление.
   * @param task Задача от которой уведомление.
   * @return Уведомление.
   */
  public static Either<Failure, Notification> create(
      int id, NotificationType type, LocalDateTime date, String message, User user, Task task) {

    var notification = new Notification();
    notification.id = id;
    notification.type = type;
    notification.date = date;
    notification.message = message;
    notification.user = user;
    notification.task = task;
    return Either.right(notification);
  }
}
