package com.htc.domain.usecases.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

//TODO: нужен ли сценарий изменения уведомления? в макете уведомления предоставлены в двух
// возможных состояниях новое(с красной точкой) и старое(без). В спецификаци таких состояний нет.
/**
 * Сценарий обновления комментария.
 */
@Component
@AllArgsConstructor
public final class UpdateNotification implements UseCase<Notification, Notification> {
  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Notification>> execute(Notification notification) {
    return repository.update(notification);
  }
}
