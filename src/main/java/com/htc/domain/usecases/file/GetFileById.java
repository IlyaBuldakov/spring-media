package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.files.File;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения задачи по ее идентификатору.
 */
@AllArgsConstructor
public final class GetFileById implements UseCase<Integer, File> {
  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, File>> execute(Integer id) {
    return repository.get(id);
  }
}


