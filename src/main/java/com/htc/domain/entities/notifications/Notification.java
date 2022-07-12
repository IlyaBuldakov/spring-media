package com.htc.domain.entities.notifications;

import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Уведомление.
 */
@AllArgsConstructor
public class Notification {
  /**
   * Индентификатор уведомления.
   *
   * @return Индентификатор уведомления.
   */
  private @Getter int id;
  /**
   * Тип уведомления.
   *
   * @return Тип уведомления.
   */
  private @Getter NotificationType type;
  /**
   * Дата получения уведомления.
   *
   * @return Дата плучения уведомления.
   */
  private @Getter LocalDateTime date;
  /**
   * Сообщение уведомления.
   *
   * @return Сообщение уведомления.
   */
  private @Getter String message;
  /**
   * Получатель уведомления.
   *
   * @return Пользователь - получатель уведомления.
   */
  private @Getter User user;
  /**
   *  Задача. Задача связанная с уведомлнием.
   *
   * @return Задача.
   */
  private @Getter Task task;

  /**
   * Создаёт тестовое уведомление.
   *
   * @return Уведомление.
   */

  public static Notification createTestNotification() {
    var id = new Random().nextInt(Integer.MAX_VALUE);
    return new Notification(
            id,
            null,
            null,
            null,
            null,
            null
    );
  }
}