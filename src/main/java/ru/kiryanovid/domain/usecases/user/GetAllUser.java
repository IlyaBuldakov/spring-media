package ru.kiryanovid.domain.usecases.user;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.users.User;
import ru.kiryanovid.domain.repositories.UserRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

/**
 * Получить список всех пользователей.
 */
@Component
public final class GetAllUser implements UseCase<Void, Iterable<User>> {
  UserRepositories repositories;

  public GetAllUser(UserRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, Iterable<User>>> execute(Void param) {

    return repositories.getAll();
  }
}
