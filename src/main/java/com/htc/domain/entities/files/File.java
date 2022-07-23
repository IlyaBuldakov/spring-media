package com.htc.domain.entities.files;

import com.htc.domain.entities.attributes.Attribute;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.tasks.Task;
import io.vavr.control.Either;
import java.time.LocalDateTime;

/**
 * Файл. Используется как приложение к задаче.
 */
public interface File {

  /**
   * Индентификатор файла.
   *
   * @return Индентификатор файла.
   */
  Id getId();

  /**
   * Имя файла.
   *
   * @return Имя файла.
   */
  Name getName();

  /**
   * Дата загрузки файла.
   *
   * @return Дата загрузки файла.
   */
  LocalDateTime getDateCreated();

  /**
   * Формат файла.
   *
   * @return Формат файла.
   */
  Format getFormat();

  /**
   * URL файла.
   *
   * @return URL файла.
   */
  Url getUrl();

  /**
   * Задача связаная с файлом.
   *
   * @return задачу.
   */
  Task getTask();

  /**
   * Формат файла.
   */
  enum Format {
    DOC,
    DOCX,
    XLS,
    XLSX,
    PDF
  }


  /**
   * Имя файла.
   */
  final class Name extends Attribute<String> {
    /**
     * Проверяет входные данные на корректность и создаёт имя файла.
     * Имя файла не должно быть пустой строкой и не должно быть длиннее 255 символов
     *
     * @param value Входные данные.
     * @return Имя файл или ошибка.
     */
    public static Either<InvalidValue, File.Name> create(String value) {
      if (value.length() == 0 || value.length() > 32) {
        return Either.left(InvalidValue.INVALID_USER_NAME);
      }

      var fileName = new File.Name(value);
      return Either.right(fileName);
    }

    private Name(String value) {
      super(value);
    }
  }

  //TODO: требуется рефаторинг.
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
    public static Either<InvalidValue, File.Url> create(String value) {
      var fileUrl = new File.Url(value);
      return Either.right(fileUrl);
    }

    private Url(String value) {
      super(value);
    }
  }
}
