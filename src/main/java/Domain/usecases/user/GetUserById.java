package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
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
