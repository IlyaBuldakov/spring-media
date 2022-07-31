package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.file.File;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения файла по его идентификатору.
 */
@AllArgsConstructor
public final class GetFileById implements UseCase<GetFileById.Params, File> {
  /**
   * Параметры сценария получения файла по его идентификатору.
   *
   * @param id идентификатор файла
   */
  public record Params(Id id) {}

  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, File>> execute(Params params) {
    return repository.get(params.id());
  }
}
