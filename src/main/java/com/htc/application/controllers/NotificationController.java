package com.htc.application.controllers;

import com.htc.infrastructure.models.user.NotificationModel;
import com.htc.infrastructure.repositories.NotificationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с уведомлениями.
 */
@RestController
@RequestMapping(path = "api/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationController {
  @Autowired
  private NotificationRepositoryImpl notificationRepository;

  /**
   * Возвращает список всех уведомлений.
   *
   * @return Список уведомлений.
   */
  @GetMapping()
  public Iterable<NotificationModel> getAll() {

    return notificationRepository.findAll();
  }
}
