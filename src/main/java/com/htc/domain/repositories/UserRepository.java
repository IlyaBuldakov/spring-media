package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.UserEmail;
import com.htc.domain.entities.utility.parameters.UserImage;
import com.htc.domain.entities.utility.parameters.UserName;
import com.htc.domain.entities.utility.parameters.UserPassword;
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
   * @param name новый пользователь
   *
   * @return user новый пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> add(UserName name, UserEmail email,
                                               UserPassword password, UserImage image, Role role);

  /**
   * Получение пользователя.
   *
   * @param id идентификатор пользователя
   *
   * @return user пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> get(Id id);

  /**
   * Получение всех пользователей.
   *
   * @return list список всех пользователей, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, List<User>>> getAll();

  /**
   * Обновление пользователя.
   *
   * @param id пользователь
   *
   * @return user пользователь, подробнее {@link User}
   */
  CompletableFuture<Either<Failure, User>> update(
          Id id, UserName name, UserEmail email, UserPassword password, UserImage image, Role role);

  /**
   * Удаление пользователя.
   *
   * @param id идентификатор пользователя
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);
}
