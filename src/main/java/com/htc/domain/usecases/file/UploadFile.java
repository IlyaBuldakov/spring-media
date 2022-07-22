package com.htc.domain.usecases.file;

import com.htc.domain.entities.File;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий загрузки файла.
 */
@Component
@AllArgsConstructor
public final class UploadFile implements UseCase<UploadFile.Params, File> {

  /**
   * Параметры сценария загрузки файла.
   *
   * @param name Название файла.
   * @param nameKey Ключ названия файла.
   * @param dateCreated Дата создания файла.
   * @param dateCreatedKey Ключ даты создания файла.
   * @param format Формат файла.
   * @param formatKey Ключ формата файла.
   * @param urlFile Путь к файлу.
   * @param urlFileKey Ключ пути пользователя.
   * @param taskId Идентификатор задачи содержащей файл.
   * @param taskIdKey Ключ идентификатора задачи содержащей файл.
   */
  public record Params(
      String name, String nameKey,
      LocalDateTime dateCreated, String dateCreatedKey,
      File.Format format, String formatKey,
      String urlFile, String urlFileKey,
      int taskId, String taskIdKey) {}

  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, File>> execute(Params params) {
    return repository.upload(
        params.name,
        params.dateCreated,
        params.format,
        params.urlFile,
        params.taskId
    );
  }
}
