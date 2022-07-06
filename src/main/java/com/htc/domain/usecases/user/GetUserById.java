package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения пользователя по его идентификатору.
 */
@Component
@AllArgsConstructor
public final class GetUserById implements UseCase<GetUserById.Params, User> {
  /**
   * Параметры сценария получения пользователя по его идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @param key Ключ идентификатора пользователя.
   */
  public record Params(Long id, String key) {}

  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Params params) {
    var id = Id.create(params.id());
    return id.isRight()
            ? repository.get(id.get())
            : EitherHelper.badLeft(new InvalidValues());
  }
}
