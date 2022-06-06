package domain.usecases.file;

import domain.entities.failures.Failure;
import domain.entities.files.File;
import domain.entities.tasks.Task;
import domain.repositories.FileRepository;
import domain.repositories.TaskRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения задачи по ее идентификатору.
 */
@AllArgsConstructor
public final class GetFileById implements UseCase<Integer, File> {
  private final FileRepository repository;

  @Override
  public Future<Either<Failure, File>> execute(Integer id) {
    return repository.get(id);
  }
}


