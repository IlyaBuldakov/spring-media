package domain.entities.content;

import domain.entities.user.User;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Контент.
 */
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
}
