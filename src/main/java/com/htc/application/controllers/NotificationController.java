package com.htc.application.controllers;

import com.htc.domain.entities.notification.Notification;
import com.htc.domain.usecases.notification.GetAllNotifications;
import com.htc.infrastructure.models.user.NotificationModel;
import com.htc.infrastructure.repositories.JpaNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Контроллер для работы с уведомлениями.
 */
@RestController
@RequestMapping(path = "api/notifications")
public class NotificationController {

  @Autowired
  GetAllNotifications getAllNotifications;

  /**
   * Возвращает список всех уведомлений.
   *
   * @return Список уведомлений.
   */
  @GetMapping
  public Iterable<Notification> getAll() throws ExecutionException, InterruptedException {
    return getAllNotifications.execute(null)
        .get()
        .get();
  }
}
