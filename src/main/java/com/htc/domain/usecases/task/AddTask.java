package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий добавления новой задачи.
 */
//@Component
@AllArgsConstructor
public final class AddTask implements UseCase<Task, Task> {
  private final TaskRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Task task) {
    return repository.add(task);
  }
}
