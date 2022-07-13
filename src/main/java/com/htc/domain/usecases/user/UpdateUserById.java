package com.htc.domain.usecases.user;

import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
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
 * Сценарий обновления данных пользователя.
 */
@Component
@AllArgsConstructor
public final class UpdateUserById implements UseCase<UpdateUserById.Params, User> {

  public record Params(
      int id, String idKey,
      String name, String nameKey,
      String email, String emailKey,
      String password, String passwordKey,
      String image, String imageKey,
      User.Role role, String roleKey) {}

  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Params params) {
    var failure = new InvalidValues();

    var id = Id.create(params.id);
    if (id.isLeft()) {
      failure.invalidValues().put(id.getLeft(), params.idKey);
    }

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
        ? repository.update(id.get(), name.get(), email.get(), password.get(), image.get(), params.role)
        : Results.fail(failure);
  }
}
