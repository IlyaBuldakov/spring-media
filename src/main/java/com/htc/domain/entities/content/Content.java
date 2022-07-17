package com.htc.domain.entities.content;

import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.files.File;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.net.URL;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Контент.
 */
public interface Content {

  /**
   * Индентификатор контента.
   *
   * @return Индентификатор контента.
   */
  Id getId();

  /**
   * Тип контента.
   *
   * @return Тип контента.
   */
  Type getType();

  /**
   * Наименование контента.
   *
   * @return Наименование контента.
   */
  String getName();

  /**
   * Дата загрузки контента.
   *
   * @return Дата загрузки контента.
   */
  LocalDateTime getDateCreated();

  /**
   * Пользователь - автор контента.
   *
   * @return Пользователь - автор контента.
   */
  User getAuthor();

  /**
   * Формат контента.
   *
   * @return Формат контента.
   */
  Format getFormat();

  /**
   * Адресс контента.
   *
   * @return Адресс контента.
   */
  Url getUrl();

  /**
   * Превью контента.
   *
   * @return Превью контента.
   */
  String getPreview();

  /**
   * Расширение файла контента.
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
   * Тип контента.
   */

  enum Type {
    /**
     * Фото.
     */
    PHOTO(1, "Фото"),
    /**
     * Видео.
     */
    VIDEO(2, "Видео"),
    /**
     * Аудио.
     */
    AUDIO(3, "Аудио");
    /**
     * Идентификатор типа контента.
     *
     * @return id Идентификатор типа контента.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter int id;

    /**
     * Наименование типа контента.
     *
     * @return Наименование типа контента.
     */
    @SuppressWarnings("JavadocDeclaration")
    private final @Getter String name;

    Type(int id, String name) {
      this.id = id;
      this.name = name;
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
     * @return адресс файла или ошибка.
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
