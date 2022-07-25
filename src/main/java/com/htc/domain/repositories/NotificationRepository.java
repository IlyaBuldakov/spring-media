package com.htc.domain.repositories;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.Notification;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий уведомление.
 */
public interface NotificationRepository {

  /**
   * Создает уведомление.
   *
   * @param type Тип уведомления.
   * @param date Дата создания уведомления.
   * @param message Сообщение уведомления
   * @param userId Пользователь связаный с уведомлением.
   * @param taskId Задача связанная с уведомлением
   * @return Уведомление или ошибку.
   */
  CompletableFuture<Either<Failure, Notification>> create(
          Notification.Type type,
          LocalDateTime date,
          Notification.Message message,
          Id userId,
          Id taskId);

  /**
   * Обновляет уведомление.
   *
   * @param notification Уведомление.
   */
  CompletableFuture<Either<Failure, Notification>> update(Notification notification);

  /**
   * Удаляет уведомление.
   *
   * @param id Идентификатор уведомления.
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);

  /**
   * Получает уведомление.
   *
   * @param id Идентификатор уведомления.
   * @return Уведомление.
   */
  CompletableFuture<Either<Failure, Notification>> get(Id id);

  /**
   * Получает список всех уведомлений.
   *
   * @return Список уведомлений.
   */
  CompletableFuture<Either<Failure, Collection<Notification>>> getAllByUser();
}
