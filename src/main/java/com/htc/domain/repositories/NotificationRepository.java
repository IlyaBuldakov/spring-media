package com.htc.domain.repositories;

import com.htc.domain.entities.content.Type;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.notification.Notification;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.Id;
import io.vavr.control.Either;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Репозиторий для уведомлений.
 */
public interface NotificationRepository {
  /**
   * Добавление нового уведомления.
   *
   * @param type тип
   * @param message сообщение
   * @param user пользователь
   * @param task задача
   *
   * @return notification новое уведомление, подробнее {@link Notification}
   */
  CompletableFuture<Either<Failure, Notification>> add(Type type, String message,
                                               User user, Task task);

  /**
   * Получение списка уведомлений.
   *
   * @return list список уведомлений, подробнее {@link Notification}
   */
  CompletableFuture<Either<Failure, List<Notification>>> getAll();

  /**
   * Удаление уведомления.
   *
   * @param id идентификатор уведомления
   */
  CompletableFuture<Either<Failure, Void>> delete(Id id);
}
