package ru.kiryanovid.domain.repositories;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.users.User;

/**
 * Репозиторий пользователей.
 */
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
   * @return Пользователь найденный по идентификатору.
   */
  CompletableFuture<Either<Failure, User>> get(Integer id);

  /**
   * Получает пользователя.
   *
   * @param email электронная почта пользователя.
   * @return Пользователь найденный по электронной почте.
   */
  CompletableFuture<Either<Failure, Integer>> getUserByEmail(String email);

  /**
   * Получает список всех пользователей.
   *
   * @return Список пользователей.
   */
  CompletableFuture<Either<Failure, Iterable<User>>> getAll();
}
