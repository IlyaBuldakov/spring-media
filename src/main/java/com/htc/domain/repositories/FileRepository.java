package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.files.File;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий файлов.
 */
public interface FileRepository {

  /**
   * Загружает файл в задачу.
   *
   * @param file Файл.
   */
  Future<Either<Failure, File>> upload(File file);

  /**
   * Удаляет файл из задачи.
   *
   * @param id Идентификатор файла.
   */
  Future<Either<Failure, Void>> delete(int id);

  /**
   * Получает файл под идентификатору задачи.
   *
   * @param taskId Идентификатор задачи.
   */
  Future<Either<Failure, Iterable<File>>> getFilesByTaskId(int taskId);
}
