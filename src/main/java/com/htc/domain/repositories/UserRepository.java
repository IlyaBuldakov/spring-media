package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий пользователя.
 */
public interface UserRepository {
  /**
   * Добавление нового пользователя.
   *
   * @param user новый пользователь
   *
   * @return user новый пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> add(User user);

  /**
   * Получение пользователя.
   *
   * @param id идентификатор пользователя
   *
   * @return user пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> get(int id);

  /**
   * Получение всех пользователей.
   *
   * @return list список всех пользователей, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, Iterable<User>>> getAll();

  /**
   * Обновление пользователя.
   *
   * @param user пользователь
   *
   * @return user пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> update(User user);

  /**
   * Удаление пользователя.
   *
   * @param id идентификатор пользователя
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);

  /**
   * Получение списка пользователей, чье имя соответствует строке запроса {@code query}.
   * Проверяется не полное соответствие строки запроса и имени пользователя, а ищется подстрока.
   *
   * @param query строка запроса
   *
   * @return list список пользователей, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, Iterable<User>>> search(String query);

  /**
   * Получение списка пользователей, чье имя и роль соответствует строке
   * запроса {@code query} и роли {@code role}. Проверяется не полное соответствие строки
   * запроса и имени пользователя, а ищется подстрока.
   *
   * @param query строка запроса
   * @param role роль пользователя, подробнее {@link Role}
   *
   * @return list список пользователей, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, Iterable<User>>> search(String query, Role role);
}
