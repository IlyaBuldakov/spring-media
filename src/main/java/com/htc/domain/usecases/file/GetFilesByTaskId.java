package com.htc.domain.usecases.file;

import com.htc.domain.entities.File;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения файлов по идентификатору задачи.
 */
@Component
@AllArgsConstructor
public final class GetFilesByTaskId implements UseCase<GetFilesByTaskId.Params, Collection<File>> {
  /**
   * Параметры сценария получения файла по его идентификатору.
   *
   * @param id Идентификатор задачи.
   * @param key Ключ идентификатора задачи.
   */
  public record Params(int id, String key) {}

  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Collection<File>>> execute(Params params) {
    var taskId = Id.create(params.id);
    if (taskId.isRight()) {
      return repository.getFilesByTaskId(taskId.get().getValue());
    }

    var failure = new InvalidValues();
    failure.invalidValues().put(taskId.getLeft(), params.key);
    return Results.fail(failure);
  }
}
