package domain.repositories;

import domain.entities.failures.Failure;
import domain.entities.user.User;
import domain.entities.user.UserRole;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.Future;


/**
 * Репозиторий пользователя.
 */
public interface UserRepository {
  /**
   * Добавление нового пользователя.
   *
   * @param user - новый пользователь
   *
   * @return user - новый пользователь, подробнее {@link User}
   */
  Future<Either<Failure, User>> add(User user);

  /**
   * Получение пользователя.
   *
   * @param id - идентификатор пользователя
   *
   * @return user - пользователь, подробнее {@link User}
   */
  Future<Either<Failure, User>> get(int id);

  /**
   * Получение всех пользователей.
   *
   * @return list - список всех пользователей, подробнее {@link User}
   */
  Future<Either<Failure, List<User>>> getAll();

  /**
   * Обновление пользователя.
   *
   * @param user - пользователь, подробнее {@link User}
   *
   * @return user - пользователь, подробнее {@link User}
   */
  Future<Either<Failure, User>> update(User user);

  /**
   * Удаление пользователя.
   *
   * @param id - идентификатор пользователя
   */
  Future<Either<Failure, Void>> delete(int id);

  /**
   * Получение списка пользователей, чье имя соответствует строке запроса {@code query}.
   *
   * @param query - строка запроса
   *
   * @return list - список пользователей, подробнее {@link User}
   */
  Future<Either<Failure, List<User>>> search(String query);

  /**
   * Получение списка пользователей, чье имя и роль соответствует
   * строке запроса {@code query} и роли {@code role}.
   *
   * @param query - строка запроса
   * @param role - роль пользователя, подробнее {@link UserRole}
   *
   * @return list - список пользователей, подробнее {@link User}
   */
  Future<Either<Failure, List<User>>> search(String query, UserRole role);
}
