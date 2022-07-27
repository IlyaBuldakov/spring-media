package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения списка всех пользователей.
 */
@AllArgsConstructor
public final class GetAllUsers implements UseCase<GetAllUsers.VoidParam, List<User>> {
  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, List<User>>> execute(VoidParam param) {
    return repository.getAll();
  }

  /**
   * Пустой параметр для сценария без параметра.
   */
  public record VoidParam() {}
}
