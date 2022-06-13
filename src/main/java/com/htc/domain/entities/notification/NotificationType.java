package com.htc.domain.entities.notification;

import lombok.Getter;

/**
 * Типы уведомлений.
 */
public enum NotificationType {
  VIDEO("Видео"),
  AUDIO("Аудио"),
  PHOTO("Фото"),
  COMMENT("Комментарий"),
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
