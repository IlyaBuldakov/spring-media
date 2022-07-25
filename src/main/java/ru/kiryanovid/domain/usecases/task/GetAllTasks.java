package ru.kiryanovid.domain.usecases.task;

import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.errors.Failure;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.repositories.TaskRepositories;
import ru.kiryanovid.domain.usecases.UseCase;

/**
 *  Получить список всех задач.
 */
@Component
public final class GetAllTasks implements UseCase<Void, Iterable<Task>> {
  private final TaskRepositories repositories;

  public GetAllTasks(TaskRepositories repositories) {
    this.repositories = repositories;
  }

  @Override
  public CompletableFuture<Either<Failure, Iterable<Task>>> execute(Void param) {
    return repositories.getAll();
  }
}
