package com.htc.infrastructure.repositories;

import com.htc.domain.entities.Notification;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.infrastructure.models.NotificationModel;
import com.htc.utility.Results;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория пользователей.
 */
@Repository
public class NotificationRepositoryImpl implements NotificationRepository {
  @Autowired
  Notifications notifications;

  @Override
  public CompletableFuture<Either<Failure, Notification>> create(
      Notification.NotificationType notificationType,
      LocalDateTime dateCreateNotification,
      String notificationMessage,
      int userId,
      int taskId
  ) {
    var notification = new NotificationModel(
        notificationType,
        dateCreateNotification,
        notificationMessage,
        userId,
        taskId);
    return Results.succeed(notifications.save(notification));
  }

  @Override
  public CompletableFuture<Either<Failure, Collection<Notification>>> getAll() {

    return Results.succeed(new ArrayList<>(notifications.findAll()));
  }

  /**
   * ORM для доступа к данным пользователей в СУБД.
   */
  public interface Notifications extends JpaRepository<NotificationModel, Integer> {

  }
}
