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
 * Сценарий добавления пользователя.
 */
@Component
@AllArgsConstructor
public final class AddUser implements UseCase<User, User> {
  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(User user) {
    return repository.add(user);
  }
}
