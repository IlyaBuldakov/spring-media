package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления пользователя по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteUserById implements UseCase<Integer, Void> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
