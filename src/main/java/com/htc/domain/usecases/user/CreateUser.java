package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.Role;
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
          Role role) {}


  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Params params) {
    return repository.create(
            User.create(
                    1,
                    params.name(),
                    params.email(),
                    params.password(),
                    params.image(),
                    params.role())
                    .get());
  }
}
