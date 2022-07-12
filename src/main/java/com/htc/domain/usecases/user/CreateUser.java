package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий создания пользователя.
 */
@Component
@AllArgsConstructor
public final class CreateUser implements UseCase<CreateUser.Params, User> {

  /**
   * Параметры для выполнения сценария.
   *
   * @param name Имя пользователя.
   * @param email Электроная почта пользователя.
   * @param password Пароль пользователя.
   * @param image Изображение пользователя.
   * @param role Роль пользователя.
   */
  public record Params(
          String name,
          String email,
          String password,
          String image,
          User.Role role) {}


  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Params params) {

    InvalidValues invalidValues = new InvalidValues();

    var name = User.Name.create(params.name);
    if (name.isLeft()) {
      invalidValues.addInvalidValue(name.getLeft());
    }

    var email = User.Email.create(params.email);
    if (email.isLeft()) {
      invalidValues.addInvalidValue(email.getLeft());
    }

    var password = User.Password.create(params.password);
    if (password.isLeft()) {
      invalidValues.addInvalidValue(password.getLeft());
    }

    var image = User.Image.create(params.image);
    if (image.isLeft()) {
      invalidValues.addInvalidValue(image.getLeft());
    }



    return invalidValues.getInvalidValues().isEmpty()
            ? repository.create(name.get(), email.get(), password.get(), image.get(), params.role)
            : CompletableFuture.completedFuture(Either.left(invalidValues));
  }

}
