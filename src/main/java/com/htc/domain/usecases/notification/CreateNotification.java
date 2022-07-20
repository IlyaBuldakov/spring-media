package com.htc.domain.usecases.notification;

import com.htc.domain.entities.Notification;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий создания задачи.
 */
@Component
@AllArgsConstructor
public class CreateNotification implements UseCase<CreateNotification.Params, Notification> {

  /**
   * Параметры сценария создания уведомления.
   *
   * @param notificationType Тип уведомления.
   * @param notificationTypeKey Ключ типа уведомления.
   * @param notificationMessage Сообщение уведомления.
   * @param notificationMessageKey Ключ сообщения уведомления.
   */
  public record Params(
      Notification.NotificationType notificationType, String notificationTypeKey,
      String notificationMessage, String notificationMessageKey) {}

  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Notification>> execute(Params params) {
    return null;
  }
}