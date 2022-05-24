package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.UserRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления пользователя по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteUserById implements UseCase<Integer, Void> {
  private final UserRepository repository;

  @Override
  public Future<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
