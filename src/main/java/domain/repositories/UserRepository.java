package domain.repositories;

import domain.entities.failures.Failure;
import domain.entities.user.RoleDto;
import domain.entities.user.UserDto;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Репозиторий пользователей.
 */
public interface UserRepository {
  /**
   * Создаёт пользователя.
   *
   * @param userDto Пользователь.
   */
  Future<Either<Failure, UserDto>> create(UserDto userDto);

  /**
   * Обновляет данные пользователя.
   *
   * @param userDto Пользователь.
   */
  Future<Either<Failure, UserDto>> update(UserDto userDto);

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
  Future<Either<Failure, UserDto>> get(int id);

  /**
   * Получает список всех пользователей.
   *
   * @return Список пользователей.
   */
  Future<Either<Failure, List<UserDto>>> getAll();

  /**
   * Получает список пользователей, чьё имя соответствует
   * строке запроса {@code query}.
   *
   * @param query Строка запроса.
   * @return Список пользователей.
   */
  Future<Either<Failure, List<UserDto>>> search(String query);

  /**
   * Получает список пользователей, чьё имя и роль соответствуют
   * строке запроса {@code query} и роли {@code role}.
   *
   * @param query Строка запроса.
   * @param role Роль пользователя.
   * @return Список пользователей.
   */
  Future<Either<Failure, List<UserDto>>> search(String query, RoleDto role);
}