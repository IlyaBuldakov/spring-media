package com.htc.domain.entities.content;

import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
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
  private @Getter Id id;

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
  private @Getter DateCreated dateCreated;

  /**
   * Идентификатор {@link com.htc.domain.entities.user.User автора контента}.
   *
   *
   * @return id идентификатор автора
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter User author;

  /**
   * {@link Format Формат} файла.
   *
   * @return format формат
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Format format;

  /**
   * Путь к превью.
   *
   * @return previewPath путь к превью
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter FileUrlPath previewPath;

  /**
   * Идентификатор {@link com.htc.domain.entities.file.File файла}.
   *
   * @return id идентификатор файла
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter File file;

  /**
   * Идентификатор {@link com.htc.domain.entities.task.Task задачи}.
   *
   * @return id идентификатор задачи
   */
  @SuppressWarnings("JavadocDeclaration")
  //TODO task
  private @Getter int taskId;
}
