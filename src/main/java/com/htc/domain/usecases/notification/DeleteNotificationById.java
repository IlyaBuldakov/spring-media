package com.htc.domain.usecases.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

/**
 * Сценарий удаления уведомления по его идентификатору.
 */
@AllArgsConstructor
public final class DeleteNotificationById implements UseCase<DeleteNotificationById.Params, Void> {
  /**
   * Параметры сценария удаления идентификатора по его идентификатору.
   *
   * @param id идентификатор уведомления
   */
  public record Params(Id id) {}

  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    return repository.delete(params.id());
  }
}
