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
 * Сценарий получения задачи по её идентификатору.
 */
@Component
@AllArgsConstructor
public class GetTaskById implements UseCase<Integer, Task> {
  private final TaskRepository repository;

  @Override
  public Future<Either<Failure, Task>> execute(Integer id) {
    return repository.get(id);
  }
}
