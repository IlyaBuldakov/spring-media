package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий пользователей.
 */
public interface UsersRepository {

    /**
     * Создание пользователя.
     *
     * @param name Имя пользователя.
     * @param password Пароль пользователя.
     * @param email E-mail пользователя.
     * @param avatar Аватар пользователя.
     * @param role Роль пользователя.
     * @return Пользователь.
     */
    CompletableFuture<Either<Failure, User>> create(String name,
                                                    String password,
                                                    String email,
                                                    String avatar,
                                                    Role role);

    /**
     * Получение пользователя по идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь.
     */
    CompletableFuture<Either<Failure, User>> getById(int id);

    /**
     * Получение списка всех пользователей.
     *
     * @return Список пользователей.
     */
    CompletableFuture<Either<Failure, List<User>>> getAll();

    /**
     * Обновление пользователя.
     *
     * @param id Идентификатор пользователя.
     * @param name Имя пользователя.
     * @param password Пароль пользователя.
     * @param email E-mail пользователя.
     * @param avatar Аватар пользователя.
     * @param role Роль пользователя.
     * @return Пользователь.
     */
    CompletableFuture<Either<Failure, User>> update(int id,
                                                    String name,
                                                    String password,
                                                    String email,
                                                    String avatar,
                                                    Role role);

    /**
     * Удаление пользователя по идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь.
     */
    CompletableFuture<Either<Failure, Void>> deleteById(int id);
}
