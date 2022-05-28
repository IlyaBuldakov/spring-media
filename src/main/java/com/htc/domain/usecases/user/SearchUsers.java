package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
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

