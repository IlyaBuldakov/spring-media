package com.htc.domain.entities;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.NonNull;

/**
 * Файл. Используется как приложение к задаче.
 *
 * @param id Индентификатор файла.
 * @param name Имя файла.
 * @param dateCreated Дата загрузки файла.
 * @param format Формат файла.
 * @param url Адрес файла.
 * @param task Задача связаная с файлом.
 */
public record File(
        Id id,
        Name name,
        LocalDateTime dateCreated,
        Format format,
        Url url,
        Task task
) implements Entity {

  /**
   * Формат файла.
   */
  public enum Format {
    DOC,
    DOCX,
    XLS,
    XLSX,
    PDF
  }


  /**
   * Имя файла.
   */
  public static final class Name extends BaseAttribute<String> {
    /**
     * Создает имя файла.
     *
     * @param value Входные данные.
     * @return Имя файл или ошибка.
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
     * Проверяет имя файла на корректность.
     *
     * <p>Требования к имени файла:</p>
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
