package com.htc.domain.usecases.notification;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notifications.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Сценарий создания уведомления.
 */
@Component
@AllArgsConstructor
public class CreateNotification implements UseCase<CreateNotification.Params, Notification> {

  /**
   * Параметры выполения сценария.
   *
   * @param type Тип уведомления.
   * @param date Дата создания уведомления.
   * @param message Текст уведомления.
   * @param userId ПОльзователь связанный с уведомлением.
   * @param taskId Задача связанная с уведомлением.
   */
  public record Params(
          Notification.Type type,
          LocalDateTime date,
          String message,
          int userId,
          int taskId) {
  }

  private final NotificationRepository repository;
  
  @Override
  public CompletableFuture<Either<Failure, Notification>> execute(
          CreateNotification.Params params) {
    var message = Notification.Message.create(params.message);

    var userId = Id.create(params.userId);
    var taskId = Id.create(params.taskId);
    return repository.create(
            params.type,
            params.date,
            message.get(),
            userId.get(),
            taskId.get());
  }
}
