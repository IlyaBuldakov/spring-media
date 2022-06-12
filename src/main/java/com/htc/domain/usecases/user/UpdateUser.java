package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий обновления данных пользователя.
 */
@Component
@AllArgsConstructor
public final class UpdateUser implements UseCase<User, User> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, User>> execute(User user) {
    return repository.update(user);
  }
}
