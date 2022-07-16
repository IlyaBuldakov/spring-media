package com.htc.domain.usecases.task;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.tasks.Task;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения задачи по ее идентификатору.
 */
@Component
@AllArgsConstructor
public final class GetTaskById implements UseCase<Integer, Task> {
  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Integer id) {
    var idEither = Id.create(id);
    if (idEither.isRight()) {
      return repository.get(idEither.get());
    }

    var invalidValues = new InvalidValues();
    invalidValues.addInvalidValue(idEither.getLeft());
    return CompletableFuture.completedFuture(Either.left(invalidValues));
  }
}


