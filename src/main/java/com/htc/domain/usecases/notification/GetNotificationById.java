package com.htc.domain.usecases.notification;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.notifications.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий получения уведомления по ее идентификатору.
 */
@Component
@AllArgsConstructor
public final class GetNotificationById implements UseCase<Integer, Notification> {
  private final NotificationRepository repository;


  @Override
  public CompletableFuture<Either<Failure, Notification>> execute(Integer id) {
    var idEither = Id.create(id);
    if (idEither.isRight()) {
      return repository.get(idEither.get());
    }

    var invalidValues = new InvalidValues();
    invalidValues.addInvalidValue(idEither.getLeft());
    return CompletableFuture.completedFuture(Either.left(invalidValues));
  }
}


