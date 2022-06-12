package com.htc.domain.entities.files;

import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Файл.
 */
public class File {

  /**
   *  Идентификатор файла.
   *
   * @return id Идентификатор файла.
   */
  private @Getter int id;

  /**
   *  Название файла.
   *
   * @return name Название файла.
   */
  private @Getter String name;

  /**
   *  Дата создания файла.
   *
   * @return dateCreated Дата создания файла.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   *  Формат файла.
   *
   * @return format Формат файла, см. {@Link FileFormat}
   */
  private @Getter FileFormat format;

  /**
   *  Путь к файлу.
   *
   * @return urlFile Путь к файлу.
   */
  private @Getter String urlFile;

  /**
   *  Идентификатор задачи, к которой прикреплен файл.
   *
   * @return taskId Идентификатор задачи, к которой прикреплен файл.
   */
  private @Getter int taskId;

  /**
   * Загружает файл к задаче.
   *
   * @param id Идентификатор файла.
   * @param name Имя файла.
   * @param dateCreated Дата создания файла.
   * @param format Формат файла.
   * @param urlFile Путь к файлу.
   * @param taskId Идентификатор задачи файла.
   * @return Файл.
   */
  public static Either<Failure, File> upload(
      int id, String name, LocalDateTime dateCreated,
      FileFormat format, String urlFile, int taskId) {

    var file = new File();
    file.id = id;
    file.name = name;
    file.dateCreated = dateCreated;
    file.format = format;
    file.urlFile = urlFile;
    file.taskId = taskId;
    return Either.right(file);
  }
}
