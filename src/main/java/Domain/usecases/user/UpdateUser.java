package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;


/**
 * Сценарий обновления пользователя.
 */
@AllArgsConstructor
public final class UpdateUser implements UseCase<User, User> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, User>> execute(User user) {
    return repository.update(user);
  }
}