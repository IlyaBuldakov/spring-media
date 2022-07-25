package com.htc.domain.entities;

import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.InvalidValue;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Медиаконтент.
 */
public interface Content {

  /**
   * Индентификатор медиаконтента.
   *
   * @return Индентификатор медиаконтента.
   */
  Id getId();

  /**
   * Тип медиаконтента.
   *
   * @return Тип медиаконтента.
   */
  Type getType();

  /**
   * Наименование медиаконтента.
   *
   * @return Наименование медиаконтента.
   */
  Name getName();

  /**
   * Дата загрузки медиаконтента.
   *
   * @return Дата загрузки медиаконтента.
   */
  LocalDateTime getDateCreated();

  /**
   * Пользователь - автор медиаконтента.
   *
   * @return Пользователь - автор медиаконтента.
   */
  User getAuthor();

  /**
   * Формат медиаконтента.
   *
   * @return Формат медиаконтента.
   */
  Format getFormat();

  /**
   * Адресс медиаконтента.
   *
   * @return Адресс медиаконтента.
   */
  Url getUrl();

  /**
   * Адрес ревью медиаконтента.
   *
   * @return Адрес превью медиаконтента.
   */
  Url getPreview();

  /**
   * Расширение файла медиаконтента.
   */
  enum Format {
    JPG,
    PNG,
    MP3,
    M4A,
    FLAC,
    AVI,
    MP4
  }

  /**
   * Тип медиаконтента.
   */

  enum Type {
    /**
     * Фото.
     */
    PHOTO(0, "Фото"),
    /**
     * Видео.
     */
    VIDEO(1, "Видео"),
    /**
     * Аудио.
     */
    AUDIO(2, "Аудио");
    /**
     * Идентификатор типа медиаконтента.
     *
     * @return Идентификатор типа медиаконтента.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter int id;

    /**
     * Наименование типа медиаконтента.
     *
     * @return Наименование типа медиаконтента.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter String name;

    Type(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  /**
   * Имя файла.
   */
  final class Name extends Attribute<String> {
    /**
     * Создаёт имя файла.
     *
     * @param value Входные данные.
     * @return Имя файла или ошибка.
     */
    public static Either<InvalidValue, Content.Name> create(String value) {
      var contentName = new Content.Name(value);
      return Either.right(contentName);
    }

    private Name(String value) {
      super(value);
    }
  }

  /**
   * Адресс файла.
   */
  final class Url extends Attribute<String> {
    /**
     * Создаёт адресс файла.
     *
     * @param value Входные данные.
     * @return Адресс файла или ошибка.
     */
    public static Either<InvalidValue, Content.Url> create(String value) {
      var contentUrl = new Content.Url(value);
      return Either.right(contentUrl);
    }
    
    private Url(String value) {
      super(value);
    }
  }
}
