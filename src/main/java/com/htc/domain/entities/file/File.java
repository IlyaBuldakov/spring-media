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
   * Идентификатор.
   *
   * @see Id#create(Long)
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Имя файла.
   *
   * @see FileName#create(String)
   *
   * @return name имя
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
   * Format файла.
   *
   * @see Format
   *
   * @return format формат
   */
  Format getFormat();

  /**
   * Путь до файла.
   *
   * @see FileUrlPath#create(String)
   *
   * @return fileUrlPath путь до файла
   */
  FileUrlPath getFileUrlPath();

  /**
   * Cодержимое файла в base64.
   *
   * @see FileBody#create(String)
   *
   * @return file содержимое файла
   */
  FileBody getFile();
}

/*
TODO
файл: типы файла в зависимости от задачи
        показать превью файла (имя+расширение, дата) превью по размеру заданному
        изменить название файла
        получить имя с расширением
        узнать размер файла
        аватар юзера - файл?
        получить файл
        добавить файл в новую задачу
        regex имя файла добавить функцию подставления в него типов файла из перечисления
проверка идентификаторов на не число - тест*/