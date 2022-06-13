package com.htc.application.controllers;

import com.htc.domain.entities.notification.Notification;
import com.htc.domain.usecases.notification.GetAllNotifications;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с уведомлениями.
 */
@RestController
@RequestMapping(path = "api/notifications")
@AllArgsConstructor
public class NotificationController {
  private GetAllNotifications getAllNotifications;

  /**
   * Возвращает список всех уведомлений.
   *
   * @return Список уведомлений.
   * @throws ExecutionException Ошибка выполения запроса.
   * @throws InterruptedException Ошибка выполения запроса.
   */
  @GetMapping
  public Iterable<Notification> getAll() throws ExecutionException, InterruptedException {
    return getAllNotifications.execute(null)
        .get()
        .get();
  }
}
