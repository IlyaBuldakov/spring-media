package finalproject.application.dto.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Тип контента.
 */
@AllArgsConstructor
public class ContentTypeDto {
  /**
   * Виды контента.
   */
  public enum ContentType {
    /**
     * Видео.
     */
    VIDEO,
    /**
     * Аудио.
     */
    AUDIO,
    /**
     * Фото.
     */
    PHOTO
  }

  /**
   * Возвращает @return id идентификатор типа контента.
   */
  private @Getter int id;

  /**
   * Возвращает @return name тип контента.
   */
  private @Getter ContentType name;
}
