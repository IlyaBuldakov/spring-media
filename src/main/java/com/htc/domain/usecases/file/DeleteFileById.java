package com.htc.domain.usecases.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.FileRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
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
   * Параметры сценария удаления файла по его идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @param key Ключ идентификатора пользователя.
   */
  public record Params(Long id, String key) {}

  private final FileRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    var id = Id.create(params.id());
    return id.isRight()
            ? repository.delete(id.get())
            : EitherHelper.badLeft(new InvalidValues());
  }
}
