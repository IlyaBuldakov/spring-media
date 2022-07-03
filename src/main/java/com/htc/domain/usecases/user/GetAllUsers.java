package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения списка всех пользователей.
 */
@Component
@AllArgsConstructor
public final class GetAllUsers implements UseCase<Void, List<User>> {
  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, List<User>>> execute(Void param) {
    return repository.getAll();
  }
}
