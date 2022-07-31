package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления файла по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteFileById implements UseCase<DeleteFileById.Params, Void> {
  /**
   * Параметры сценария удаления файла по его идентификатору.
   *
   * @param id идентификатор пользователя
   */
  public record Params(Id id) {}

  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    return repository.delete(params.id());
  }
}
