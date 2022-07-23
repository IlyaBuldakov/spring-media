package com.htc.domain.usecases.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notification.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения списка уведомлений.
 */
//@Component
@AllArgsConstructor
public final class GetAllNotifications implements UseCase<Void, List<Notification>> {
  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, List<Notification>>> execute(Void param) {
    return repository.getAll();
  }
}
