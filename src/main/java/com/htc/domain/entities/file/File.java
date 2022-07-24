package com.htc.domain.entities.file;

import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileBody;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;

/**
 * Файл.
 */
public interface File {
  /**
   * Получить идентификатор файла.
   *
   * @see Id#create(Long)
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Получить имя файла.
   *
   * @see FileName#create(String)
   *
   * @return name имя
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
   * Получить формат файла.
   *
   * @see Format
   *
   * @return format формат
   */
  Format getFormat();

  /**
   * Получить путь до файла.
   *
   * @see FileUrlPath#create(String)
   *
   * @return fileUrlPath путь до файла
   */
  FileUrlPath getFileUrlPath();

  /**
   * Получить содержимое файла в base64.
   *
   * @see FileBody#create(String)
   *
   * @return file содержимое файла
   */
  FileBody getFile();
}
