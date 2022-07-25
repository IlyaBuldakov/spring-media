package ru.kiryanovid.domain.usecases.user;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.repositories.UserRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

/**
 * Обновление пользователя.
 */
@Component
public final class UpdateUser implements UseCase<User, User> {
  UserRepositories repositories;

  public UpdateUser(UserRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, User>> execute(User user) {
    repositories.update(user);
    return null;
  }
}
