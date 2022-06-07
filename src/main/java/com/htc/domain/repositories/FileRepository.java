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
   * Созает файл.
   *
   * @param file Файл.
   */
  Future<Either<Failure, File>> create(File file);

  /**
   * Обновляет файл.
   *
   * @param file Файл.
   */
  Future<Either<Failure, File>> update(File file);

  /**
   * Удаляет файл.
   *
   * @param id Идентификатор файла.
   */
  Future<Either<Failure, Void>> delete(int id);

  /**
   * Получает файл.
   *
   * @param id Идентификатор файла.
   * @return Файл.
   */
  Future<Either<Failure, File>> get(int id);

  /**
   * Получает список всех файлов.
   *
   * @return Список файлов.
   */
  Future<Either<Failure, Iterable<File>>> getAll();
}
