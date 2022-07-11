package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserName;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий обновления пользователя.
 */
@Component
@AllArgsConstructor
public final class UpdateUserById implements UseCase<UpdateUserById.Params, User> {
  /**
   * Параметры сценария обновления пользователя по его идентификатору.
   *
   * @param name имя пользователя
   * @param nameKey ключ имени пользователя
   * @param email адрес электронной почты
   * @param emailKey ключ адреса электронной почты
   * @param password пароль
   * @param passwordKey ключ пароля
   * @param image изображение
   * @param imageKey ключ изображения
   * @param role роль
   * @param roleKey ключ роли
   */
  public record Params(Long id, String idKey,
                       String name, String nameKey,
                       String email, String emailKey,
                       String password, String passwordKey,
                       String image, String imageKey,
                       Role role, String roleKey) {}

  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Params params) {
    var failure = new InvalidValues();
    var id = Id.create(params.id());
    if (id.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_ID, params.idKey);
    }
    var name = UserName.create(params.name());
    if (name.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_ENTITY_NAME, params.nameKey);
    }
    var email = UserEmail.create(params.email());
    if (email.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_USER_EMAIL, params.emailKey);
    }
    var password = UserPassword.create(params.password());
    if (password.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_USER_PASSWORD, params.passwordKey);
    }
    var image = UserImage.create(params.image());
    if (image.isLeft()) {
      failure.getValues().put(InvalidValueParam.INVALID_USER_IMAGE, params.imageKey);
    }
    return failure.getValues().size() == 0
            ? repository.update(
                    id.get(),
                    name.get(),
                    email.get(),
                    password.get(),
                    image.get(),
                    params.role())
            : EitherHelper.badLeft(failure);
  }
}
