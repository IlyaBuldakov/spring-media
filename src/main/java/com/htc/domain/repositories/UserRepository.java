package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий пользователей.
 */
public interface UserRepository {
  /**
   * Создаёт пользователя.
   *
   * @param user Пользователь.
   */
  Future<Either<Failure, User>> create(User user);

  /**
   * Обновляет данные пользователя.
   *
   * @param user Пользователь.
   */
  Future<Either<Failure, User>> update(User user);

  /**
   * Удаляет пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  Future<Either<Failure, Void>> delete(int id);

  /**
   * Получает пользователя.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  Future<Either<Failure, User>> get(int id);

  /**
   * Получает список всех пользователей.
   *
   * @return Список всех пользователей.
   */
  Future<Either<Failure, Iterable<User>>> getAll();

  /**
   * Получает список пользователей, чье имя соответствует.
   * строке запроса {@code query}. Проверяется не полное соответствие строки
   * запроса и имени пользователя, а ищется подстрока.
   *
   * @param query Строка поиска.
   * @return Список пользователей.
   */
  Future<Either<Failure, Iterable<User>>> search(String query);

  /**
   * Получает список пользователей, чьё имя и роль соответствуют
   * строке поиска {@code query} и роли {@code role}. Проверяется не полное
   * соответствие строки поиска и имени пользователя, а ищется подстрока.
   *
   * @param query Строка поика.
   * @param role Роль пользователя.
   * @return Список пользователей.
   */
  Future<Either<Failure, Iterable<User>>> search(String query, Role role);
}
