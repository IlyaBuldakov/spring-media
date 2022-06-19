package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.Role;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Реализация сценария поиска пользователей по параметрам
 *
 * @author IlyaBuldakov
 */
@AllArgsConstructor
@Component
public class SearchUsers implements UseCase<SearchUsers.Query, Iterable<User>> {
    /**
     * Параметры запроса поиска пользователей.
     *
     * @param query Строка поиска
     * @param role Роль пользователя
     */
    public record Query(String query, Role role) {}

    private UsersRepository usersRepository;

    @Override
    public CompletableFuture<Either<Failure, Iterable<User>>> execute(Query query) {
        return query.role() == null
                ? usersRepository.search(query.query())
                : usersRepository.search(query.query(), query.role());
    }

    public Void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        return null;
    }
}

