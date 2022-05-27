package domain.usecases.user;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.entities.user.Role;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.concurrent.Future;

/**
 * Реализация сценария поиска пользователей по параметрам
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public final class SearchUsers implements UseCase<SearchUsers.Query, Iterable<User>> {
    /**
     * Параметры запроса поиска пользователей.
     *
     * @param query Строка поиска
     * @param role Роль пользователя
     */
    public record Query(String query, Role role) {}

    private final UserRepository repository;

    @Override
    public Future<Either<Failure, Iterable<User>>> execute(SearchUsers.Query query) {
        return query.role() == null
                ? repository.search(query.query())
                : repository.search(query.query(), query.role());
    }
}

