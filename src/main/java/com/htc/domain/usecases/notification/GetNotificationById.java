package com.htc.domain.usecases.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notifications.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий получения уведомления по ее идентификатору.
 */
@AllArgsConstructor
public final class GetNotificationById implements UseCase<Integer, Notification> {
  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Notification>> execute(Integer id) {
    return repository.get(id);
  }
}


