package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий удаления пользователя по его идентификатору.
 */
@Component
@AllArgsConstructor
public final class DeleteUserById implements UseCase<Integer, Void> {
  private final UserRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
