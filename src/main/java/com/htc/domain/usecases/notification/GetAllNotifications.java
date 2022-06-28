package com.htc.domain.usecases.notification;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notification.Notification;
import com.htc.domain.repositories.NotificationRepository;
import com.htc.domain.usecases.UseCase;
import com.htc.infrastructure.repositories.JpaNotificationRepository;
import io.vavr.control.Either;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Сценарий получения списка всех уведомлений.
 */
@Component
@AllArgsConstructor
public class GetAllNotifications implements UseCase<Void, Iterable<Notification>> {

  @Autowired
  JpaNotificationRepository repository;

  @Override
  public Future<Either<Failure, Iterable<Notification>>> execute(Void param) {
    return repository.getAll();
  }
}