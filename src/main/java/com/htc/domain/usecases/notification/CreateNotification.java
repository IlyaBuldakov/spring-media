package com.htc.domain.usecases.notification;

import com.htc.domain.entities.Notification;
import com.htc.domain.entities.User;
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

  public record Params(
      Notification.NotificationType notificationType,
      String notificationMessage, String emailKey,
      String password, String passwordKey,
      String image, String imageKey,
      User.Role role, String roleKey) {}

  private final NotificationRepository repository;

  @Override
  public CompletableFuture<Either<Failure, Notification>> execute(Params params) {
    return null;
  }
}