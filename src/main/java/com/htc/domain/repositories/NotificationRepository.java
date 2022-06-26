package com.htc.domain.repositories;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notifications.Notification;
import io.vavr.control.Either;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий уведомление.
 */
public interface NotificationRepository {
  /**
   * Созает уведомление.
   *
   * @param notification Уведомление.
   */
  CompletableFuture<Either<Failure, Notification>> create(Notification notification);

  /**
   * Обновляет уведомление.
   *
   * @param notification Уведомление.
   */
  CompletableFuture<Either<Failure, Notification>> update(Notification notification);

  /**
   * Удаляет уведомление.
   *
   * @param id Идентификатор Уведомления.
   */
  CompletableFuture<Either<Failure, Void>> delete(int id);

  /**
   * Получает уведомление.
   *
   * @param id Идентификатор уведомления.
   * @return Уведомление.
   */
  CompletableFuture<Either<Failure, Notification>> get(int id);

  /**
   * Получает список всех уведомлений.
   *
   * @return Список уведомлений.
   */
  CompletableFuture<Either<Failure, Iterable<Notification>>> getAll();
}
