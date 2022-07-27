package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserName;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий добавления пользователя.
 */

@AllArgsConstructor
public final class AddUser implements UseCase<AddUser.Params, User> {
  /**
   * Параметры сценария добавления пользователя.
   *
   * @param name имя пользователя
   * @param email адрес электронной почты
   * @param password пароль
   * @param image изображение
   * @param role роль
   */
  public record Params(UserName name,
                       UserEmail email,
                       UserPassword password,
                       UserImage image,
                       Role role) {}

  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Params params) {
    return repository.add(params.name(), params.email(),
            params.password(), params.image(), params.role());
  }
}
