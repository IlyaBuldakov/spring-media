package com.htc.domain.repositories;

import com.htc.domain.entities.File;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий файлов.
 */
public interface FileRepository {

  /**
   * Загружает файл в задачу.
   *
   * @param name Название файла.
   * @param dateCreated Дата загрузки файла.
   * @param format Формат файла.
   * @param urlFile Путь к файлу.
   * @param taskId Идентификатор задачи файла.
   * @return Файл или ошибку.
   */
  CompletableFuture<Either<Failure, File>> upload(
      String name,
      LocalDateTime dateCreated,
      File.Format format,
      String urlFile,
      int taskId
  );

  /**
   * Получает файл.
   *
   * @param id Идентификатор файла.
   * @return Файл.
   */
  CompletableFuture<Either<Failure, File>> get(Id id);

  /**
   * Получает файлы под идентификатору задачи.
   *
   * @param taskId Идентификатор задачи.
   */
  CompletableFuture<Either<Failure, Collection<File>>> getFilesByTaskId(int taskId);

  /**
   * Удаляет файл из задачи.
   *
   * @param id Идентификатор файла.
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);
}
