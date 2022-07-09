package ru.kiryanovid.domain.repositories;

import io.vavr.control.Either;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.users.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface UserRepositories {
    /**
     * Создаёт пользователя.
     *
     * @param user Пользователь.
     */
    CompletableFuture<Either<Failure, User>> create(User user);

    /**
     * Обновляет пользователя.
     *
     * @param user Пользователь.
     */
    CompletableFuture<Either<Failure, User>> update(User user);

    /**
     * Удаляет пользователя.
     *
     * @param id Идентификатор пользователь.
     */
    CompletableFuture<Either<Failure, Void>> delete(Integer id);

    /**
     * Получает пользователя.
     *
     * @param id Идентификатор пользователь.
     * @return Пользователь.
     */
    CompletableFuture<Either<Failure, User>> get(Integer id);

    /**
     * Получает список всех пользователей.
     *
     * @return Список пользователей.
     */
    CompletableFuture<Either<Failure, Iterable<User>>> getAll();
}
