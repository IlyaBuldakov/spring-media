package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notification.Notification;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий для уведомлений.
 */
public interface NotificationRepository {
  /**
   * Получение списка уведомлений.
   *
   * @return list - список уведомлений, подробнее {@link Notification}
   */
  Future<Either<Failure, Iterable<Notification>>> getAll();
}
