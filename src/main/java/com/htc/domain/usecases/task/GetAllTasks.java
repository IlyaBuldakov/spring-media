package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.Task;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения списка всех задач.
 */
@Component
@AllArgsConstructor
public final class GetAllTasks implements UseCase<Void, Collection<Task>> {
  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Collection<Task>>> execute(Void param) {
    return repository.getAll();
  }
}
