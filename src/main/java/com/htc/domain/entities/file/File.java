package com.htc.domain.entities.file;

import lombok.Getter;

/**
 * Файл.
 */
public class File {
  /**
   * Идентификатор файла.
   *
   * @return id идентификатор
   */
  private @Getter int id;

  /**
   * Имя файла.
   *
   * @return name имя
   */
  private @Getter String name;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  private @Getter String dateCreated;

  /**
   * {@link Format Формат} файла.
   *
   * @return format формат
   */
  private @Getter Format format;

  /**
   * Путь до {@link File файла}.
   *
   * @return fileUrlPath путь до файла
   */
  private @Getter String fileUrlPath;

  /**
   * содержимое файла.
   *
   *<p>Формат Base64.</p>
   *
   * @return file содержимое файла
   */
  private @Getter String file;

  /**
   * Идентификатор {@link com.htc.domain.entities.task.Task задачи}.
   *
   * @return id идентификатор задачи
   */
  private @Getter int taskId;
}
