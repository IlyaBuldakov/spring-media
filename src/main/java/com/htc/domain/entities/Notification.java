package com.htc.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htc.domain.entities.attributes.Id;
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
  Id getId();

  /**
   * Тип уведомления.
   *
   * @return type Тип уведомления, см. {@Link NotificationType}
   */
  NotificationType getNotificationType();

  /**
   * Дата создания уведомления.
   *
   * @return date Дата уведомления.
   */
  LocalDateTime getDateCreateNotification();

  /**
   * Сообщение уведомления.
   *
   * @return message Сообщение уведомления.
   */
  String getNotificationMessage();

  /**
   * Пользователь для которого уведомление.
   *
   * @return user Пользователь для которого уведомление, см. {@Link User}
   */
  int getUserId();

  /**
   * Задача от которой уведомление.
   *
   * @return task Задача от которой уведомление, см. {@Link Task}
   */
  int getTaskId();

  /**
   * Тип уведомления.
   */
  enum NotificationType {
    /**
     * Видео.
     */
    @JsonProperty("video")
    VIDEO("Видео"),
    /**
     * Аудио.
     */
    @JsonProperty("audio")
    AUDIO("Аудио"),
    /**
     * Фото.
     */
    @JsonProperty("photo")
    PHOTO("Фото"),
    /**
     * Комментарий.
     */
    @JsonProperty("comment")
    COMMENT("Комментарий"),
    /**
     * Контент.
     */
    @JsonProperty("content")
    CONTENT("Контент");

    /**
     * Название типа уведомления.
     *
     * @return name Название типа уведомления.
     */
    private final @Getter String name;

    NotificationType(String name) {
      this.name = name;
    }
  }
}
