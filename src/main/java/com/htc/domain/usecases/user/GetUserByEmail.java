package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения пользователя по его почте.
 */
@AllArgsConstructor
public final class GetUserByEmail implements UseCase<GetUserByEmail.Params, User> {
  /**
   * Параметры сценария получения пользователя по его почте.
   *
   * @param email почта пользователя
   */
  public record Params(UserEmail email) {
  }

  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Params params) {
    return repository.get(params.email());
  }
}
