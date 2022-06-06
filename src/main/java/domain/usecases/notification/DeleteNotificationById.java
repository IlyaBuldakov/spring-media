package domain.usecases.notification;

import domain.entities.failures.Failure;
import domain.repositories.NotificationRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления уведомления по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteNotificationById implements UseCase<Integer, Void> {
  private final NotificationRepository repository;

  @Override
  public Future<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
