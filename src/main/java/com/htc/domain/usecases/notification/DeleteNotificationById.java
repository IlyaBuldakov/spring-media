package com.htc.domain.usecases.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления уведомления по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteNotificationById implements UseCase<Integer, Void> {
  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Integer id) {
    return repository.delete(id);
  }
}
