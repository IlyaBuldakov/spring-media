package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.Role;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий пользователей
 *
 * @author IlyaBuldakov
 */
public interface UsersRepository {

    /**
     * Создание пользователя
     *
     * @param user Пользователь
     */
    CompletableFuture<Either<Failure, User>> create(User user);

    /**
     * Обновление данных пользователя
     *
     * @param user Пользователь
     */
    CompletableFuture<Either<Failure, User>> update(User user);

    /**
     * Удаление пользователя
     *
     * @param id Идентификатор пользователя
     */
    CompletableFuture<Either<Failure, Void>> delete(int id);

    /**
     * Получение пользователя
     *
     * @param id Идентификатор пользователя
     * @return Пользователь
     */
    CompletableFuture<Either<Failure, User>> get(int id);

    /**
     * Получение списка всех пользователей
     *
     * @return Список пользователей
     */
    CompletableFuture<Either<Failure, List<User>>> getAll();

    /**
     * Получение списка пользователей, соответствующих запросу
     *
     * @param query Запрос
     * @return Список пользователей
     */
    CompletableFuture<Either<Failure, Iterable<User>>> search(String query);

    /**
     * Получение списка пользователей, соответствующих запросу и роли
     *
     * @param query Запрос
     * @param role  Роль
     * @return Список пользователей
     */
    CompletableFuture<Either<Failure, Iterable<User>>> search(String query, Role role);


}
