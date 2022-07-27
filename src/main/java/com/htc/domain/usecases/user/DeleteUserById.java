package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
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
   */
  public record Params(Id id) {}

  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    return repository.delete(params.id);
  }
}
