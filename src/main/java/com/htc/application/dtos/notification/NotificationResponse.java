package com.htc.application.dtos.notification;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.notification.Notification;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности уведомления.
 */
@EqualsAndHashCode
public class NotificationResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Long id;

  /**
   * {@link Type Тип}.
   *
   * @return type тип
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Type type;

  /**
   * Дата уведомления.
   *
   * @return dateNotification дата уведомления
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String dateNotification;

  /**
   * Текст.
   *
   * @return message текст
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String message;

  /**
   * Пользователь.
   *
   * @return user пользователь
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter User user;

  /**
   * Задача.
   *
   * @return task задача
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Task task;

  /**
   * Создание основного представления уведомления.
   *
   * @param notification сущность уведомления, подробнее {@link Notification}
   */
  public NotificationResponse(Notification notification) {
    this.id = notification.getId().getValue();
    this.type = notification.getType();
    this.dateNotification = notification.getDateNotification().getValue();
    this.message = notification.getMessage();
    this.user = notification.getUser();
    this.task = notification.getTask();
  }
}
