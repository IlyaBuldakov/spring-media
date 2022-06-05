package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий для файла.
 */
public interface FileRepository {
  /**
   * Загрузка файла в задачу.
   *
   * @param file - файл
   *
   * @return file - файл, подробнее {@link File}
   */
  Future<Either<Failure, File>> upload(File file);

  /**
   * Удаление файла по идентификатору.
   *
   * @param fileId - идентификатор файла
   */
  Future<Either<Failure, Void>> delete(int fileId);
}
