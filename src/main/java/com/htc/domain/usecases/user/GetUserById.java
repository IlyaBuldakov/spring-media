package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения пользователя по его идентификатору.
 */
@AllArgsConstructor
public final class GetUserById implements UseCase<Integer, User> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, User>> execute(Integer id) {
    return repository.get(id);
  }
}


