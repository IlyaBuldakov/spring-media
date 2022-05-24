package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения списка всех пользователей.
 */
@AllArgsConstructor
public final class GetAllUsers implements UseCase<Void, Iterable<User>> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, Iterable<User>>> execute(Void param) {
    return repository.getAll();
  }
}
