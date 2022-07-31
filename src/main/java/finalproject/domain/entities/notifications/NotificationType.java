package finalproject.domain.entities.notifications;

import lombok.Getter;

/**
 * Перечисление типа уведомлений.
 */
public enum NotificationType {
  VIDEO("видео"),
  AUDIO("аудио"),
  PHOTO("изображения"),
  COMMENT("комментария"),
  CONTENT("контента");

  @Getter
  public String toMessage;

  NotificationType(String toMessage) {
    this.toMessage = toMessage;

  }
}