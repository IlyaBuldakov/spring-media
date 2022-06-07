package com.htc.domain.usecases.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notifications.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
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
