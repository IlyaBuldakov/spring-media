package com.htc.domain.usecases.user;

import com.htc.domain.entities.User;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.Results;
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

  public record Params(
      String name, String nameKey,
      String email, String emailKey,
      String password, String passwordKey,
      String image, String imageKey,
      User.Role role, String roleKey) {}

  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Params params) {
    var failure = new InvalidValues();

    var name = User.Name.create(params.name);
    if (name.isLeft()) {
      failure.invalidValues().put(name.getLeft(), params.nameKey);
    }

    var email = User.Email.create(params.email);
    if (email.isLeft()) {
      failure.invalidValues().put(email.getLeft(), params.emailKey);
    }

    var password = User.Password.create(params.password);
    if (password.isLeft()) {
      failure.invalidValues().put(password.getLeft(), params.passwordKey);
    }

    var image = User.Image.create(params.image);
    if (image.isLeft()) {
      failure.invalidValues().put(image.getLeft(), params.imageKey);
    }

    return failure.invalidValues().size() == 0
        ? repository.create(name.get(), email.get(), password.get(), image.get(), params.role)
        : Results.fail(failure);
  }
}
