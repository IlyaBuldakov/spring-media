package ru.kiryanovid.domain.usecases.task;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

/**
 * Обновить задачу.
 */
@Component
public final class UpdateTask implements UseCase<Task, Task> {
  private final TaskRepositories repositories;

  public UpdateTask(TaskRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Task task) {
    repositories.update(task);
    return null;
  }
}
