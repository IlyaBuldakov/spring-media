package com.htc.domain.usecases.file;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий удаления файла по его идентификатору.
 */
@Component
@AllArgsConstructor
public final class DeleteFileById implements UseCase<DeleteFileById.Params, Void> {
  /**
   * Параметры сценария получения задачи по ее идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @param key Ключ идентификатора пользователя.
   */
  public record Params(int id, String key) {}

  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    var id = Id.create(params.id);
    if (id.isRight()) {
      return repository.delete(id.get());
    }

    var failure = new InvalidValues();
    failure.invalidValues().put(id.getLeft(), params.key);
    return Results.fail(failure);
  }
}
