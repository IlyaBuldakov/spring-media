package com.htc.domain.usecases.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий удаления уведомления по его идентификатору.
 */
//@Component
@AllArgsConstructor
public final class DeleteNotificationById implements UseCase<DeleteNotificationById.Params, Void> {
  /**
   * Параметры сценария удаления идентификатора по его идентификатору.
   *
   * @param id Идентификатор уведомления.
   * @param key Ключ идентификатора уведомления.
   */
  public record Params(Long id, String key) {}

  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Params params) {
    var id = Id.create(params.id());
    return id.isRight()
            ? repository.delete(id.get())
            : EitherHelper.badLeft(new InvalidValues());
  }
}
