package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.entities.user.UserRole;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения списка пользователей в соответствии с запросом.
 */
@AllArgsConstructor
public final class SearchUsers implements UseCase<SearchUsers.Query, Iterable<User>> {
  /**
   * Параметры запроса поиска пользователей.
   *
   * @param query Строка запроса.
   * @param role Роль пользователя.
   */
  public record Query(String query, UserRole role) {}

  private final UserRepository repository;

  @Override
  public Future<Either<Failure, Iterable<User>>> execute(SearchUsers.Query query) {
    return query.role() == null
        ? repository.search(query.query())
        : repository.search(query.query(), query.role());
  }
}
