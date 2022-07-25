package ru.kiryanovid.domain.usecases.user;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.repositories.UserRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

/**
 * Получить пользователя по идентификатору.
 */
@Component
public final class GetUserById implements UseCase<Integer, User> {
  private final UserRepositories repositories;

  public GetUserById(UserRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Integer id) {
    return repositories.get(id);
  }
}
