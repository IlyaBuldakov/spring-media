package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.files.File;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий обновления файла.
 */
@AllArgsConstructor
public final class UpdateFile implements UseCase<File, File> {
  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, File>> execute(File file) {
    return repository.update(file);
  }
}
