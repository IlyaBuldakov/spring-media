package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления задачи по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteFileById implements UseCase<Integer, Void> {
  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
