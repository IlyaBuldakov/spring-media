package domain.usecases.content;

import domain.entities.failures.Failure;
import domain.repositories.ContentRepository;
import domain.repositories.NotificationRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления контента по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteContentById implements UseCase<Integer, Void> {
  private final ContentRepository repository;

  @Override
  public Future<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
