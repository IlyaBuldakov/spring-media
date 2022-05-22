package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.UserRole;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения списка пользователей в соответствии с запросом.
 */
@AllArgsConstructor
public final class SearchUsers implements UseCase<SearchUsers.Query, Iterable<User>> {
  /**
   * Параметры запроса поиска пользователя.
   *
   * @param query - строка запроса
   * @param role - роль пользователя
   */
  public record Query(String query, UserRole role) {}

  private final UserRepository repository;

  @Override
  public Future<Either<Failure, Iterable<User>>> execute(SearchUsers.Query query) {
    return (query.role() == null)
        ? repository.search(query.query())
        : repository.search(query.query(), query.role());
  }
}
