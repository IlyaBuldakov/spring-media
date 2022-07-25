package com.htc.domain.usecases.file;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.File;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий создания файла.
 */
@Component
@AllArgsConstructor
public final class CreateFile implements UseCase<CreateFile.Params, File> {

  /**
   * Параметры выполнеия сценария.
   *
   * @param name Имя файла.
   * @param dateCreated Дата создания файла.
   * @param format Формат файла.
   * @param url Адрес файла.
   * @param taskId Индентификатор родительской задачи.
   */
  public record Params(
          String name,
          LocalDateTime dateCreated,
          File.Format format,
          String url,
          int taskId) {
  }

  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, File>> execute(CreateFile.Params params) {
    InvalidValues invalidValues = new InvalidValues();

    var name = File.Name.create(params.name);
    if (name.isLeft()) {
      invalidValues.addInvalidValue(name.getLeft());
    }
    var url = File.Url.create(params.url);
    if (url.isLeft()) {
      invalidValues.addInvalidValue(url.getLeft());
    }
    var taskId = Id.create(params.taskId);
    if (taskId.isLeft()) {
      invalidValues.addInvalidValue(taskId.getLeft());
    }

    return repository.create(
            name.get(),
            LocalDateTime.now(),
            params.format,
            url.get(),
            taskId.get());
  }
}
