package domain.usecases.notification;

import domain.entities.failures.Failure;
import domain.entities.notifications.Notification;
import domain.repositories.NotificationRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения уведомления по ее идентификатору.
 */
@AllArgsConstructor
public final class GetNotificationById implements UseCase<Integer, Notification> {
  private final NotificationRepository repository;

  @Override
  public Future<Either<Failure, Notification>> execute(Integer id) {
    return repository.get(id);
  }
}


