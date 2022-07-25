package com.htc.infrastructure.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.File;
import com.htc.domain.repositories.FileRepository;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория файлов.
 */
@Repository
@AllArgsConstructor
public class FileRepositoryImpl implements FileRepository {

  @Override
  public CompletableFuture<Either<Failure, File>> create(
          File.Name name,
          LocalDateTime dateCreated,
          File.Format format,
          File.Url url,
          Id taskId) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, File>> update(File file) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, File>> get(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<File>>> getAll() {
    return null;
  }
}
