package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.entities.user.UserDto;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения пользователя по его идентификатору.
 */
@AllArgsConstructor
public final class GetUserById implements UseCase<Integer, UserDto> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, UserDto>> execute(Integer param) {
    repository.get(1);
    return null;
  }
}