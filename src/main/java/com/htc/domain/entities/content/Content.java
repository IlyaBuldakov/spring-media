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
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int id;

  /**
   * {@link Type Тип} контента.
   *
   * @return type тип
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Type type;

  /**
   * Наименование контента.
   *
   * @return name наименование
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String name;

  /**
   * Дата создания.
   *
   * @return dateCreated дата создания
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String dateCreated;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User автора контента}.
   *
   *
   * @return id идентификатор автора
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int authorId;

  /**
   * {@link Format Формат} файла.
   *
   * @return format формат
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Format format;

  /**
   * Путь до {@link com.htc.domain.entities.file.File файла}.
   *
   * @return fileUrlPath путь до файла
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String fileUrlPath;

  /**
   * Путь к превью.
   *
   * @return previewPath путь к превью
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String previewPath;

  /**
   * Идентификатор {@link com.htc.domain.entities.file.File файла}.
   *
   * @return id идентификатор файла
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int fileId;

  /**
   * Идентификатор {@link com.htc.domain.entities.task.Task задачи}.
   *
   * @return id идентификатор задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int taskId;
}
