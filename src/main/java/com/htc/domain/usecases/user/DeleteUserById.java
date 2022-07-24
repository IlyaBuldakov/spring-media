package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления пользователя по его идентификатору.
 */

@AllArgsConstructor
public final class DeleteUserById implements UseCase<DeleteUserById.Params, Void> {
  /**
   * Параметры сценария удаления пользователя по его идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @param key Ключ идентификатора пользователя.
   */
  public record Params(Long id, String key) {}

  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    var id = Id.create(params.id());
    return id.isRight()
            ? repository.delete(id.get())
            : EitherHelper.badLeft(new InvalidValues());
  }
}
