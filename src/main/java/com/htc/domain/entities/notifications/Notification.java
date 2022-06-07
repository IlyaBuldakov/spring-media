package com.htc.domain.entities.notifications;

import com.htc.domain.entities.tasks.Task;
import com.htc.domain.entities.user.User;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.AllArgsConstructor;

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
  private int id;
  /**
   * Тип уведомления.
   *
   * @return Тип уведомления.
   */
  private NotificationType type;
  /**
   * Дата получения уведомления.
   *
   * @return Дата плучения уведомления.
   */
  private LocalDateTime date;
  /**
   * Сообщение уведомления.
   *
   * @return Сообщение уведомления.
   */
  private String message;
  /**
   * Получатель уведомления.
   *
   * @return Пользователь - получатель уведомления.
   */
  private User user;
  /**
   *  Задача. Задача связанная с уведомлнием.
   *
   * @return Задача.
   */
  private Task task;

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
