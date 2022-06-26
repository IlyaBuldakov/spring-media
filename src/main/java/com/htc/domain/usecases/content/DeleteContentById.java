package com.htc.domain.usecases.content;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.ContentRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления контента по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteContentById implements UseCase<Integer, Void> {
  private final ContentRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
