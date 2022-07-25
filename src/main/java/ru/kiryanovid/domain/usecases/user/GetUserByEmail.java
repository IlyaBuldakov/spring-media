package ru.kiryanovid.domain.usecases.user;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.repositories.UserRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

/**
 * Получение пользователя по электронной почте.
 */

@Component
public final class GetUserByEmail implements UseCase<String, Integer> {
  private final UserRepositories repositories;

  public GetUserByEmail(UserRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, Integer>> execute(String email) {
    return repositories.getUserByEmail(email);
  }
}
