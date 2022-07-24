package ru.kiryanovid.domain.usecases.user;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.repositories.UserRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

/**
 * Удаление пользователя по идентификатору.
 */
@Component
public final class DeleteUserById implements UseCase<Integer, Void> {
  private final UserRepositories repositories;

  public DeleteUserById(UserRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Integer id) {
    repositories.delete(id);
    return null;
  }
}
