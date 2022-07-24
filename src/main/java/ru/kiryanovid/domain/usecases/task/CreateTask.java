package ru.kiryanovid.domain.usecases.task;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;


/**
 * Создать задачу.
 */
@Component
public final class CreateTask implements UseCase<Task, Task> {
  private final TaskRepositories repositories;

  public CreateTask(TaskRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Task task) {
    return repositories.create(task);
  }
}
