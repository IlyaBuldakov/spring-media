package domain.usecases.task;

import domain.entities.failures.Failure;
import domain.entities.tasks.Task;
import domain.repositories.TaskRepository;
import domain.repositories.UserRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления задачи по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteTaskById implements UseCase<Integer, Void> {
  private final TaskRepository repository;

  @Override
  public Future<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
