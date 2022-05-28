package domain.repositories;

import domain.entities.failures.Failure;
import domain.entities.user.Role;
import domain.entities.user.User;
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
   * Удаляет пользоваетеля.
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
   * @return Список пользователей.
   */
  Future<Either<Failure, Iterable<User>>> getAll();

  /**
   * Получает список пользователей, чьё имя соответствует
   * строке запроса {@code query}.
   *
   * @param query Строка запроса.
   * @return Список пользователей.
   */
  Future<Either<Failure, Iterable<User>>> search(String query);

  /**
   * Получает список пользователей, чьё имя и роль соответствуют
   * строке запроса {@code query} и роли {@code role}.
   *
   * @param query Строка запроса.
   * @param role  Роль пользователя.
   * @return Список пользователей.
   */
  Future<Either<Failure, Iterable<User>>> search(String query, Role role);
}
