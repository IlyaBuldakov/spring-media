package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.file.FileBody;
import com.htc.domain.entities.utility.parameters.file.FileName;
import com.htc.domain.entities.utility.parameters.file.FileUrlPath;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий для файла.
 */
public interface FileRepository {
  /**
   * Загрузка нового файла в задачу.
   *
   * @param name имя файла
   * @param dateCreated дата создания
   * @param format форма файла, подробнее {@link Format}
   * @param fileUrlPath путь до файла
   * @param file содержимое файла, в формате base64
   * @return result файл, подробнее {@link File}
   */
  CompletableFuture<Either<Failure, File>> upload(FileName name,
                                                  DateCreated dateCreated,
                                                  Format format,
                                                  FileUrlPath fileUrlPath,
                                                  FileBody file);

  /**
   * Получение файла.
   *
   * @param fileId идентификатор файла
   *
   * @return file файл, подробнее {@link File}
   */
  CompletableFuture<Either<Failure, File>> get(Id fileId);

  /**
   * Удаление файла по идентификатору.
   *
   * @param fileId идентификатор файла
   */
  CompletableFuture<Either<Failure, Void>> delete(Id fileId);
}
