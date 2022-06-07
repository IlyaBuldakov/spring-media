package com.htc.domain.entities.content;

import com.htc.domain.entities.user.User;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Контент.
 */
@AllArgsConstructor
public class Content {

  /**
   * Индентификатор контента.
   *
   * @return Индентификатор контента.
   */
  private @Getter int id;
  /**
   * Тип контента.
   *
   * @return Тип контента.
   */
  private @Getter ContentType type;
  /**
   * Наименование контента.
   *
   * @return Наименование контента.
   */
  private @Getter String name;
  /**
   * Дата загрузки контента.
   *
   * @return Дата загрузки контента.
   */
  private @Getter LocalDateTime dateCreated;
  /**
   * Пользователь - автор контента.
   *
   * @return Пользователь - автор контента.
   */
  private @Getter User author;
  /**
   * Формат контента.
   *
   * @return Формат контента.
   */
  private @Getter ContentFormat format;
  /**
   * Адресс контента.
   *
   * @return Адресс контента.
   */
  private @Getter String url;
  /**
   * Превью контента.
   *
   * @return Превью контента.
   */
  private @Getter String preview;


  /**
   * Создаёт тестовый объект контента.
   *
   * @return Контент.
   */

  public static Content createTestContent(int id) {
    return new Content(
            id,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    );
  }

  public static Content createTestContent() {
    var id = new Random().nextInt(Integer.MAX_VALUE);
    return createTestContent(id);
  }
}
