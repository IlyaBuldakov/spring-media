package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notifications.Notification;
import io.vavr.control.Either;
import java.util.concurrent.Future;

/**
 * Репозиторий уведомление.
 */
public interface NotificationRepository {
  /**
   * Созает уведомление.
   *
   * @param notification Уведомление.
   */
  Future<Either<Failure, Notification>> create(Notification notification);

  /**
   * Обновляет уведомление.
   *
   * @param notification Уведомление.
   */
  Future<Either<Failure, Notification>> update(Notification notification);

  /**
   * Удаляет уведомление.
   *
   * @param id Идентификатор Уведомления.
   */
  Future<Either<Failure, Void>> delete(int id);

  /**
   * Получает уведомление.
   *
   * @param id Идентификатор уведомления.
   * @return Уведомление.
   */
  Future<Either<Failure, Notification>> get(int id);

  /**
   * Получает список всех уведомлений.
   *
   * @return Список уведомлений.
   */
  Future<Either<Failure, Iterable<Notification>>> getAll();
}
