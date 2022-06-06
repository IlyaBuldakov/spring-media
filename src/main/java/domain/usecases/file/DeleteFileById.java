package domain.usecases.file;

import domain.entities.failures.Failure;
import domain.repositories.FileRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления задачи по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteFileById implements UseCase<Integer, Void> {
  private final FileRepository repository;

  @Override
  public Future<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
