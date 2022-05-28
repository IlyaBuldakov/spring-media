package domain.usecases.task;

import domain.entities.failures.Failure;
import domain.entities.tasks.Task;
import domain.repositories.TaskRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий обновления задачи.
 */
@AllArgsConstructor
public final class UpdateTask implements UseCase<Task, Task> {
  private final TaskRepository repository;

  @Override
  public Future<Either<Failure, Task>> execute(Task task) {
    return repository.update(task);
  }
}
