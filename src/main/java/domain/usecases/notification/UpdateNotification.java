package domain.usecases.notification;

import domain.entities.failures.Failure;
import domain.entities.notifications.Notification;
import domain.repositories.NotificationRepository;
import domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;

/**
 * Сценарий обновления комментария.
 */
@AllArgsConstructor
public final class UpdateNotification implements UseCase<Notification, Notification> {
  private final NotificationRepository repository;

  @Override
  public Future<Either<Failure, Notification>> execute(Notification notification) {
    return repository.update(notification);
  }
}
