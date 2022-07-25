package com.htc.domain.usecases.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения списка всех уведомлений пользователя.
 */
@Component
@AllArgsConstructor
public final class GetAllNotificationsByUser implements UseCase<Void, Collection<Notification>> {
  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Collection<Notification>>> execute(Void param) {
    return repository.getAllByUser();
  }
}
