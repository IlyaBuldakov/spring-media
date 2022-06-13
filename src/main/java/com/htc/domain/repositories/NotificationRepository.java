package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notification.Notification;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий уведомлений.
 */
public interface NotificationRepository {
  /**
   * Создаёт уведомление.
   *
   * @param notification Уведомление.
   */
  Future<Either<Failure, Notification>> create(Notification notification);

  /**
   * Получает список всех уведомлений.
   *
   * @return Список всех уведомлений.
   */
  Future<Either<Failure, Iterable<Notification>>> getAll();
}
