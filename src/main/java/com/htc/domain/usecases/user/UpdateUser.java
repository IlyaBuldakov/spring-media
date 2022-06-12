package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий обновления пользователя.
 */
@Component
@AllArgsConstructor
public final class UpdateUser implements UseCase<User, User> {
  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(User user) {
    return repository.update(user);
  }
}
