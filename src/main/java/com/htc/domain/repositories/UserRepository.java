package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import io.vavr.control.Either;
import java.util.List;
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
  CompletableFuture<Either<Failure, List<User>>> getAll();

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

  /**/
}
