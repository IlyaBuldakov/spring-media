package com.htc.domain.repositories;

import com.htc.domain.entities.File;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

/**
 * Репозиторий файлов.
 */
public interface FileRepository {

  /**
   * Создает файл.
   *
   * @param name Имя файла.
   * @param dateCreated Дата создания файла.
   * @param format Формат файла.
   * @param url Адрес файла.
   * @param task Индентификатор родительской задачи.
   * @return Созданный файл
   */
  File create(
          File.Name name,
          LocalDateTime dateCreated,
          File.Format format,
          File.Url url,
          Task task);

  /**
   * Удаляет файл.
   *
   * @param id Идентификатор файла.
   */
  void delete(Id id);

  /**
   * Получает файл.
   *
   * @param id Идентификатор файла.
   * @return Файл.
   */
  Optional<File> get(Id id);

  /**
   * Получает список всех файлов.
   *
   * @return Список файлов.
   */
  Collection<File> getAllByTask(Task task);

  /**
   * Проверяет, существует ли файл с указанным идентификатором.
   *
   * @param id Идентификатор файла.
   * @return Результат проверки.
   */
  boolean exists(Id id);
}
