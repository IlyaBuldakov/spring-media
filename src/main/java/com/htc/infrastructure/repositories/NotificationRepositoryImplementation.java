package com.htc.infrastructure.repositories;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notification.Notification;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.infrastructure.models.notification.NotificationModel;
import com.htc.infrastructure.models.task.TaskModel;
import com.htc.infrastructure.models.user.UserModel;
import com.htc.utility.EitherHelper;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория уведомления.
 */
@Repository
@AllArgsConstructor
public class NotificationRepositoryImplementation implements NotificationRepository {
  Notifications notifications;

  @Override
  public CompletableFuture<Either<Failure, Notification>> add(Type type,
                                                              DateCreated dateNotification,
                                                              String message,
                                                              User user,
                                                              Task task) {
    var notificationModel = new NotificationModel(
            0L,
            type.getName(),
            dateNotification.getValue(),
            message,
            (UserModel) user,
            (TaskModel) task
    );
    return EitherHelper.goodRight(notifications.save(notificationModel));
  }

  @Override
  public CompletableFuture<Either<Failure, List<Notification>>> getAll() {
    var notificatonList = notifications.findAll()
            .stream().map(notificationModel -> (Notification) notificationModel).toList();
    return EitherHelper.goodRight(notificatonList);
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    notifications.deleteById(id.getValue());
    return EitherHelper.goodRight(null);
  }
}
