package com.htc.application.controllers;

import com.htc.application.dto.notification.NotificationDto;
import com.htc.domain.usecases.notification.CreateNotification;
import com.htc.domain.usecases.notification.DeleteNotificationById;
import com.htc.domain.usecases.notification.GetAllNotificationsByUser;
import com.htc.domain.usecases.notification.GetNotificationById;
import com.htc.domain.usecases.notification.UpdateNotification;
import com.htc.utility.Controllers;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping(path = "api/notification")
@AllArgsConstructor
public class NotificationController {
  private DeleteNotificationById deleteNotificationById;
  private GetNotificationById getNotificationById;
  private GetAllNotificationsByUser getAllNotifications;

  /**
   * Возвращает пользователя.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  @GetMapping(path = "/{id}")
  @Async
  public CompletableFuture<NotificationDto> get(@PathVariable Integer id) {
    return Controllers.handleRequest(
            getNotificationById,
            id,
            NotificationDto::new);
  }

  /**
   * Возвращает список всех пользователей.
   *
   * @return Список пользователей.
   */
  @GetMapping
  public CompletableFuture<Collection<NotificationDto>> getAll() {
    return Controllers.handleRequest(
            getAllNotifications,
            null,
            notification -> notification.stream()
                    .map(NotificationDto::new)
                    .collect(Collectors.toList()));
  }

  /**
   * Удаляет пользователя.
   *
   * @param id Идентификатор пользователя.
   */
  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable Integer id) {
    Controllers.handleRequest(
            deleteNotificationById,
            id,
            null);
  }
}
