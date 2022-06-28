package com.htc.infrastructure.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notification.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.infrastructure.models.user.NotificationModel;
import io.vavr.control.Either;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;

@Repository
public interface JpaNotificationRepository extends NotificationRepository {

  Future<Either<Failure, Iterable<Notification>>> getAll();
}
