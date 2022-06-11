package com.htc.domain.entities.content;

import com.htc.domain.entities.file.Format;
import lombok.Getter;

/**
 * Контент.
 */
public class Content {
  /**
   * Идентификатор контента.
   *
   * @return id идентификатор
   */
  private @Getter int id;

  /**
   * {@link Type Тип} контента.
   *
   * @return type тип
   */
  private @Getter Type type;

  /**
   * Наименование контента.
   *
   * @return name наименование
   */
  private @Getter String name;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  private @Getter String dateCreated;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User автора контента}.
   *
   *
   * @return id идентификатор автора
   */
  private @Getter int authorId;

  /**
   * {@link Format Формат} файла.
   *
   * @return format формат
   */
  private @Getter Format format;

  /**
   * Путь до {@link com.htc.domain.entities.file.File файла}.
   *
   * @return fileUrlPath путь до файла
   */
  private @Getter String fileUrlPath;

  /**
   * Путь к превью.
   *
   * @return previewPath путь к превью
   */
  private @Getter String previewPath;

  /**
   * Идентификатор {@link com.htc.domain.entities.file.File файла}.
   *
   * @return id идентификатор файла
   */
  private @Getter int fileId;

  /**
   * Идентификатор {@link com.htc.domain.entities.task.Task задачи}.
   *
   * @return id идентификатор задачи
   */
  private @Getter int taskId;
}
