package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий пользователей.
 */
public interface UserRepository {
  /**
   * Создаёт пользователя.
   *
   * @param user Пользователь.
   */
  CompletableFuture<Either<Failure, User>> create(User user);

  /**
   * Обновляет данные пользователя.
   *
   * @param user Пользователь.
   */
  CompletableFuture<Either<Failure, User>> update(User user);

  /**
   * Удаляет пользоваетеля.
   *
   * @param id Идентификатор пользователя.
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);

  /**
   * Получает пользователя.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  CompletableFuture<Either<Failure, User>> get(int id);

  /**
   * Получает список всех пользователей.
   *
   * @return Список пользователей.
   */
  CompletableFuture<Either<Failure, Iterable<User>>> getAll();

}
