package finalproject.application.dto.content;

import finalproject.application.dto.user.UserBasicDto;
import finalproject.application.dto.user.UserDto;
import finalproject.domain.entities.content.Content;
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
    jpg,
    png,
    mp3,
    m4a,
    flac,
    avi,
    mp4
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
  private @Getter String dateCreated;

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

  public ContentDto(Content content) {
    this.id = content.getId();
    this.type = new ContentTypeDto(content.getType().getId(), content.getType().getName());
    this.name = content.getName();
    this.dateCreated = content.getDateCreated().toString();
    this.author = new UserDto(content.getTask().getContentMaker());
    this.format = Format.valueOf(content.getContentFormat().name().toLowerCase());
    this.url = content.getUrl();
    this.preview = content.getPreview();

  }


}
