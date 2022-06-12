package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.user.User;
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
public final class GetUserById implements UseCase<Integer, User> {
  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, User>> execute(Integer id) {
    if (id < 1) {
      return EitherHelper.badLeft(NotFound.DEFAULT_MESSAGE);
    }
    return repository.get(id);
  }
}
