package com.htc.infrastructure.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.notifications.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.infrastructure.models.NotificationModel;
import com.htc.infrastructure.models.TaskModel;
import com.htc.infrastructure.models.UserModel;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория уведомлений.
 */
@Repository
@AllArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

  Notifications notifications;

  UserRepositoryImpl userRepository;

  TaskRepositoryImpl taskRepository;

  @Override
  public CompletableFuture<Either<Failure, Notification>> create(
          Notification.Type type,
          LocalDateTime date,
          Notification.Message message,
          Id userId,
          Id taskId) {
    var user = userRepository.get(userId).join();
    var task = taskRepository.get(taskId).join();
    if (user.isLeft()) {
      return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
    }
    if (task.isLeft()) {
      return CompletableFuture.completedFuture(Either.left(NotFound.DEFAULT_MESSAGE));
    }

    var notification = new NotificationModel(
            Id.create(0).get(),
            type,
            date,
            message,
            (UserModel) user.get(),
            (TaskModel) task.get());
    return CompletableFuture.completedFuture(Either.right(notifications.save(notification)));
  }

  @Override
  public CompletableFuture<Either<Failure, Notification>> update(Notification notification) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Notification>> get(Id id) {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Notification>>> getAllByUser() {
    return null;
  }

  @Override
  public CompletableFuture<Either<Failure, Void>> delete(Id id) {
    notifications.deleteById(id.getValue());
    return CompletableFuture.completedFuture(Either.right(null));
  }

  /**
   * ORM для доступа к данным уведомлений в СУБД.
   */
  public interface Notifications extends JpaRepository<NotificationModel, Integer> {

  }
}
