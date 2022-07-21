package com.htc.domain.usecases.notification;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий удаления уведомления по его идентификатору.
 */
@Component
@AllArgsConstructor
public final class DeleteNotificationById implements UseCase<Integer, Void> {
  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Void>> execute(Integer id) {
    var idEither = Id.create(id);

    if (idEither.isRight()) {
      return repository.delete(idEither.get());
    }

    var invalidValues = new InvalidValues();
    invalidValues.addInvalidValue(idEither.getLeft());
    return CompletableFuture.completedFuture(Either.left(invalidValues));
  }
}
