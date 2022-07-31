package com.htc.domain.repositories;

import com.htc.domain.entities.Notification;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.User;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

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
   * @param user Пользователь связаный с уведомлением.
   * @param task Задача связанная с уведомлением
   * @return Уведомление или ошибку.
   */
  Notification create(
          Notification.Type type,
          LocalDateTime date,
          Notification.Message message,
          User user,
          Task task);

  /**
   * Удаляет уведомление.
   *
   * @param id Идентификатор уведомления.
   */
  void delete(Id id);

  /**
   * Получает уведомление.
   *
   * @param id Идентификатор уведомления.
   * @return Уведомление.
   */
  Optional<Notification> get(Id id);

  /**
   * Получает список всех уведомлений.
   *
   * @return Список уведомлений.
   */
  Collection<Notification> getAll();

  /**
   * Проверяет, существует ли уведомление с указанным идентификатором.
   *
   * @param id Идентификатор уведомления.
   * @return Результат проверки.
   */
  boolean exists(Id id);
}
