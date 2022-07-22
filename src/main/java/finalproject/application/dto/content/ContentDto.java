package finalproject.application.dto.content;

import finalproject.application.dto.user.UserBasicDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * DTO контента.
 */
@AllArgsConstructor
public class ContentDto {

  /**
   * Форматы контента.
   */
  public enum Format {
    JPG,
    PNG,
    MP3,
    M4A,
    FLAC,
    AVI,
    MP4
  }

  /**
   * Возвращает @return id.
   */
  private @Getter int id;

  /**
   * Возвращает @return ContentTypeDto type тип контента.
   */
  private @Getter ContentTypeDto type;

  /**
   * Возвращает @return String name название контента.
   */
  private @Getter String name;

  /**
   * Возвращает @return LocalDateTime dateCreated дату создания контента.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   * Возвращает @return UserBasicDto author автора контента.
   */
  private @Getter UserBasicDto author;

  /**
   * Возвращает @return Format формат контента.
   */
  private @Getter Format format;

  /**
   * Возвращает @return строку пути к файлу.
   */
  private @Getter String url;

  /**
   * Возвращает @return строку пути к превью.
   */
  private @Getter String preview;


}
