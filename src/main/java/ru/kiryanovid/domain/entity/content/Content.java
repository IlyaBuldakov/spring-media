package ru.kiryanovid.domain.entity.content;

import java.nio.file.Path;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.kiryanovid.domain.entity.task.ContentType;
import ru.kiryanovid.domain.entity.users.User;


/**
 * Контент.
 */
@AllArgsConstructor
@NoArgsConstructor
public class Content {
  /**
  * Идентификатор контента.
  */
  @Getter private Integer id;

  /**
  * Тип контента.
  */
  @Getter private ContentType contentType;

  /**
  * Название контента.
  */
  @Getter private String name;

  /**
  * Дата создания.
  */
  @Getter private LocalDateTime dateCreated;

  /**
  * Автор контента.
  */
  @Getter private User author;

  /**
  * Тип контента.
  */
  @Getter private ContentFormat format;

  /**
  * Путь к файлу.
  */
  @Getter private Path url;

  /**
  * Путь к превью.
  */
  @Getter private Path preview;

}
