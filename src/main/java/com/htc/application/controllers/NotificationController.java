package com.htc.application.controllers;

import com.htc.application.dtos.notification.NotificationResponse;
import com.htc.domain.usecases.notification.DeleteNotificationById;
import com.htc.domain.usecases.notification.GetAllNotifications;
import com.htc.utility.ControllerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
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
@AllArgsConstructor
@RestController
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Уведомления"))
@RequestMapping(path = "api/notifications")
public class NotificationController {
  private GetAllNotifications getAllNotifications;
  private DeleteNotificationById deleteNotificationById;

  /**
   * Получение списка уведомлений.
   *
   * @return list список пользователей
   */
  @Async
  @Operation(summary = "Получить список уведомлений.")
  @GetMapping
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
  @Async
  @Operation(summary = "Удалить уведомление по идентификатору.")
  @DeleteMapping(path = "/{id}")
  public CompletableFuture<Void> delete(@PathVariable Long id) {
    return ControllerHelper.customRequest(
            deleteNotificationById,
            new DeleteNotificationById.Params(id, "id"),
            null
    );
  }
}
