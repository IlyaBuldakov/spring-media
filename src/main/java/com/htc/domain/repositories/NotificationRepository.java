package com.htc.domain.repositories;

import com.htc.domain.entities.Notification;
import com.htc.domain.entities.failures.Failure;
import io.vavr.control.Either;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий уведомлений.
 */
@Repository
public interface NotificationRepository {
  /**
   * Создает уведомление.
   *
   * @param notificationType Тип уведомления.
   * @param dateCreateNotification Дата создания уведомления.
   * @param notificationMessage Сообщение уведомления.
   * @param userId Иденификатор пользователя для которого уведомление.
   * @param taskId Идентификатор задачи, которой принадлежит уведомление.
   * @return Уведомление или ошибка.
   */
  CompletableFuture<Either<Failure, Notification>> create(
      Notification.NotificationType notificationType,
      LocalDateTime dateCreateNotification,
      String notificationMessage,
      int userId,
      int taskId
  );

  /**
   * Получает список всех уведомлений.
   *
   * @return Список всех уведомлений.
   */
  CompletableFuture<Either<Failure, Collection<Notification>>> getAll();
}
