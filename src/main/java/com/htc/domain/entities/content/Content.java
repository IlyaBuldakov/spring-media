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
   * Получить идентификатор контента.
   *
   * @see Id#create(Long)
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Получить {@link Type тип} контента.
   *
   * @return type тип
   */
  Type getType();

  /**
   * Получить наименование контента.
   *
   * @return name наименование
   */
  FileName getName();

  /**
   * Получить дату создания.
   *
   * @see DateCreated#create()
   *
   * @return dateCreated дата создания
   */
  DateCreated getDateCreated();

  /**
   * Получить идентификатор {@link User автора контента}.
   *
   * @return id идентификатор автора
   */
  User getAuthor();

  /**
   * Получить формат {@link Format контента}.
   *
   * @return format формат
   */
  Format getFormat();

  /**
   * Получить путь к превью.
   *
   * @see FileUrlPath#create(String)
   *
   * @return previewPath путь к превью
   */
  FileUrlPath getPreviewPath();

  /**
   * Получить идентификатор {@link File файла}.
   *
   * @return id идентификатор файла
   */
  File getFile();

  /**
   * Получить идентификатор {@link Task задачи}.
   *
   * @return id идентификатор задачи
   */
  Task getTask();
}
