package com.htc.domain.entities.content;

import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;

/**
 * Контент.
 */
public interface Content {
  /**
   * Идентификатор.
   *
   * @see Id#create(Long)
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * {@link Type Тип} контента.
   *
   * @return type тип
   */
  Type getType();

  /**
   * Наименование контента.
   *
   * @return name наименование
   */
  FileName getName();

  /**
   * Дата создания.
   *
   * @see DateCreated#create()
   *
   * @return dateCreated дата создания
   */
  DateCreated getDateCreated();

  /**
   * Идентификатор {@link User автора контента}.
   *
   * @return id идентификатор автора
   */
  User getAuthor();

  /**
   * Формат {@link Format контента}.
   *
   * @return format формат
   */
  Format getFormat();

  /**
   * Путь к превью.
   *
   * @see FileUrlPath#create(String)
   *
   * @return previewPath путь к превью
   */
  FileUrlPath getPreviewPath();

  /**
   * Идентификатор {@link File файла}.
   *
   * @return id идентификатор файла
   */
  File getFile();

  /**
   * Идентификатор {@link Task задачи}.
   *
   * @return id идентификатор задачи
   */
  Task getTask();
}
