package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий пользователей
 *
 * @author IlyaBuldakov
 */
public interface UsersRepository {

    CompletableFuture<Either<Failure, User>> create(String name,
                                                    String password,
                                                    String email,
                                                    String avatar,
                                                    Role role);

    /**
     * Получение пользователя
     *
     * @param id Идентификатор пользователя
     * @return Пользователь
     */
    CompletableFuture<Either<Failure, User>> getById(int id);

    /**
     * Получение списка всех пользователей
     *
     * @return Список пользователей
     */
    CompletableFuture<Either<Failure, List<User>>> getAll();

    CompletableFuture<Either<Failure, User>> update(int id,
                                                    String name,
                                                    String password,
                                                    String email,
                                                    String avatar,
                                                    Role role);

    /**
     * Удаление пользователя
     *
     * @param id Идентификатор пользователя
     */
    CompletableFuture<Either<Failure, User>> deleteById(int id);
}
