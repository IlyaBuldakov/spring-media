package ru.kiryanovid.domain.usecases.task;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

/**
 * Получить задачу по идентификатору.
 */
@Component
public final class GetTaskById implements UseCase<Integer, Task> {
  private final TaskRepositories repositories;

  public GetTaskById(TaskRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, Task>> execute(Integer id) {
    return repositories.get(id);
  }
}
