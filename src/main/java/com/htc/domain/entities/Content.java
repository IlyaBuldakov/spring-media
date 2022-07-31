package com.htc.domain.entities;

import com.htc.domain.entities.attributes.BaseAttribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.Getter;
import lombok.NonNull;

/**
 * Медиаконтент.
 *
 * @param id Индентификатор медиаконтента.
 * @param type Тип медиаконтента.
 * @param name Наименование медиаконтента.
 * @param dateCreated Дата загрузки медиаконтента.
 * @param author Пользователь - автор медиаконтента.
 * @param format Формат медиаконтента.
 * @param contentUrl Адресс медиаконтента.
 * @param previewUrl Адрес миниатюры медиаконтента.
 */
public record Content(
        Id id,
        Type type,
        Name name,
        LocalDateTime dateCreated,
        User author,
        Format format,
        Url contentUrl,
        Url previewUrl
) implements Entity {

  /**
   * Расширение файла медиаконтента.
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
   * Тип медиаконтента.
   */

  public enum Type {
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
  public static final class Name extends BaseAttribute<String> {
    /**
     * Создаёт имя медиаконтента.
     *
     * @param value Входные данные.
     * @return Имя медиаконтента или ошибка.
     */
    public static Either<Collection<Failure>, Name> create(@NonNull String value) {
      final var name = new Name();
      name.setValue(value);

      final var failures = name.validate();
      return failures.isEmpty()
              ? Either.right(name)
              : Either.left(failures);
    }

    /**
     * Проверяет имя пользователя на корректность.
     *
     * <p>Требования к имени пользователя:</p>
     * <ol>
     *   <li>Имя не должно быть пустым, см. {@link TooShort}.</li>
     *   <li>Имя не должно быть длиннее 255 символов, см. {@link TooLong}.</li>
     * </ol>
     *
     * @return Список ошибок.
     */
    @Override
    public Collection<Failure> validate() {
      final var value = super.getValue();
      return new ValidationResultBuilder()
              .addIf(new TooShort(), value.length() == 0)
              .addIf(new TooLong(), value.length() > 255)
              .build();
    }

    /**
     * Имя пользователя слишком короткое.
     */
    public record TooShort() implements Failure {
    }

    /**
     * Имя пользователя слишком длинное.
     */
    public record TooLong() implements Failure {
    }
  }

  /**
   * Адресс файла.
   */
  public static final class Url extends BaseAttribute<String> {
    /**
     * Создаёт адресс файла.
     *
     * @param value Входные данные.
     * @return Адресс файла или ошибка.
     */
    public static Either<Collection<Failure>, Url> create(@NonNull String value) {
      final var url = new Url();
      url.setValue(value);

      final var failures = url.validate();
      return failures.isEmpty()
              ? Either.right(url)
              : Either.left(failures);
    }

    @Override
    protected Collection<Failure> validate() {
      final var value = super.getValue();
      return new ValidationResultBuilder()
              .addIf(new TooShort(), value.length() == 0)
              .build();
    }

    /**
     * Имя пользователя слишком короткое.
     */
    public record TooShort() implements Failure {
    }
  }
}
