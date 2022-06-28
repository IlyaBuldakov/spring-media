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
public interface Notification {

  /**
   * Идентификатор уведомления.
   *
   * @return id Идентификатор уведомления.
   */
  Long id = null;

  /**
   * Тип уведомления.
   *
   * @return type Тип уведомления, см. {@Link NotificationType}
   */
  NotificationType type = null;

  /**
   * Дата уведомления.
   *
   * @return date Дата уведомления.
   */
  LocalDateTime date = null;

  /**
   * Сообщение уведомления.
   *
   * @return message Сообщение уведомления.
   */
  String message = null;

  /**
   * Пользователь для которого уведомление.
   *
   * @return user Пользователь для которого уведомление, см. {@Link User}
   */
  User user = null;

  /**
   * Задача от которой уведомление.
   *
   * @return task Задача от которой уведомление, см. {@Link Task}
   */
  Task task = null;
}
