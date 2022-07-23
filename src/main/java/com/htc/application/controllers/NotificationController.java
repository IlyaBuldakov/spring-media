package com.htc.application.controllers;

import com.htc.application.dtos.notification.NotificationResponse;
import com.htc.domain.usecases.notification.DeleteNotificationById;
import com.htc.domain.usecases.notification.GetAllNotifications;
import com.htc.utility.ControllerHelper;
import java.util.List;
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
 * Контроллер уведомления.
 */
@RestController
@RequestMapping(path = "api/notifications")
@AllArgsConstructor
public class NotificationController {
  private GetAllNotifications getAllNotifications;
  private DeleteNotificationById deleteNotificationById;

  /**
   * Получение списка уведомлений.
   *
   * @return list список пользователей
   */
  @GetMapping
  @Async
  public CompletableFuture<List<NotificationResponse>> getAll() {
    return ControllerHelper.customRequest(
            getAllNotifications,
            null,
            users -> users.stream()
                    .map(NotificationResponse::new)
                    .collect(Collectors.toList())
    );
  }

  /**
   * Удаление уведомления.
   *
   * @param id идентификатор
   */
  @DeleteMapping(path = "/{id}")
  @Async
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteNotificationById,
            new DeleteNotificationById.Params(id, "id"),
            null
    );
  }
}
