package com.htc.domain.usecases.task;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.task.Task;
import com.htc.domain.repositories.TaskRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий изменения задачи.
 */
@Component
@AllArgsConstructor
public final class UpdateTask implements UseCase<Task, Task> {
  private final TaskRepository repository;

  @Override
  public Future<Either<Failure, Task>> execute(Task task) {
    return repository.update(task);
  }
}
