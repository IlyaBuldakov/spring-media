package com.htc.domain.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.File;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

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
   * @param taskId Индентификатор родительской задачи.
   * @return Файл или ошибку
   */
  CompletableFuture<Either<Failure, File>> create(
          File.Name name,
          LocalDateTime dateCreated,
          File.Format format,
          File.Url url,
          Id taskId);

  /**
   * Обновляет файл.
   *
   * @param file Файл.
   */
  CompletableFuture<Either<Failure, File>> update(File file);

  /**
   * Удаляет файл.
   *
   * @param id Идентификатор файла.
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);

  /**
   * Получает файл.
   *
   * @param id Идентификатор файла.
   * @return Файл.
   */
  CompletableFuture<Either<Failure, File>> get(Id id);

  /**
   * Получает список всех файлов.
   *
   * @return Список файлов.
   */
  CompletableFuture<Either<Failure, Collection<File>>> getAll();
}
