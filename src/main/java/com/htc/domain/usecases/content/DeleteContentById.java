package com.htc.domain.usecases.content;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления контента по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteContentById implements UseCase<DeleteContentById.Params, Void> {
  /**
   * Параметры сценария удаления контента по его идентификатору.
   *
   * @param id идентификатор контента
   */
  public record Params(Id id) {}

  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    return repository.delete(params.id());
  }
}
