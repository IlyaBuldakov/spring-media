package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
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
   * @param name имя пользователя
   * @param email почта пользователя
   * @param password пароль пользователя
   * @param image изображение пользователя
   * @param role роль пользователя
   * @return user новый пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> add(EntityName name, UserEmail email,
                                               UserPassword password, UserImage image, Role role);

  /**
   * Получение пользователя.
   *
   * @param id идентификатор пользователя
   * @return user пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> get(Id id);

  /**
   * Получение пользователя.
   *
   * @param email почта пользователя
   * @return user пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> get(UserEmail email);

  /**
   * Получение всех пользователей.
   *
   * @return list список всех пользователей, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, List<User>>> getAll();

  /**
   * Обновление пользователя.
   *
   * @param id идентификатор пользователя
   * @param name имя пользователя
   * @param email почта пользователя
   * @param password пароль пользователя
   * @param image изображение пользователя
   * @param role роль пользователя
   * @return user новый пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> update(
          Id id, EntityName name, UserEmail email,
          UserPassword password, UserImage image, Role role);

  /**
   * Удаление пользователя.
   *
   * @param id идентификатор пользователя
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);
}
