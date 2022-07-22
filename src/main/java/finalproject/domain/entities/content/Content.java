package finalproject.domain.entities.content;

import finalproject.domain.entities.user.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность - единица контента.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
public class Content {

  /**
   * Возвращает @return id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  /**
   * Возвращает @return ContentTypeDto type тип контента.
   */
  @Column
  private ContentType type;

  /**
   * Возвращает @return String name название контента.
   */
  @Column
  private @Getter String name;

  /**
   * Возвращает @return LocalDateTime dateCreated дату создания контента.
   */
  @Column
  private @Getter LocalDateTime dateCreated;

  /**
   * Возвращает @return UserBasicDto author автора контента.
   */
  @Column
  private @Getter User author;

  /**
   * Возвращает @return Format формат контента.
   */
  @Column
  private @Getter ContentFormat contentFormat;


  /**
   * Возвращает @return строку пути к превью.
   */
  @Column
  private String preview;

  /**
   * Возвращает @return task идентификатор задачи.
   */
  @Column
  private @Getter int task;

  /**
   * Возвращает @return строку пути к файлу.
   */
  @Column
  private @Getter String url;

  @Column
  private @Getter Boolean isPublished;


}
