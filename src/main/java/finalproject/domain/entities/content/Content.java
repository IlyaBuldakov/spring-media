package finalproject.domain.entities.content;

import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
public class Content {

  /**
   * Возвращает @return id.
   */
  private @Getter int id;

  /**
   * Возвращает @return ContentTypeDto type тип контента.
   */
  private @Getter ContentType type;

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
  private @Getter User author;

  /**
   * Возвращает @return Format формат контента.
   */
  private @Getter Format format;

  /**
   * Возвращает @return byte[] file файл контента.
   */
  private @Getter byte[] file;

  /**
   * Возвращает @return строку пути к превью.
   */
  private @Getter String preview;

  /**
   * Возвращает @return task идентификатор задачи.
   */
  private @Getter int task;

  /**
   * Возвращает @return строку пути к файлу.
   */
  private @Getter String url;


}
