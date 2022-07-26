package com.htc.application.controllers;

import com.htc.application.dto.notification.NotificationResponse;
import com.htc.domain.usecases.notification.GetAllNotifications;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с уведомлениями.
 */
@RestController
@RequestMapping(path = "api/notifications")
@AllArgsConstructor
@Tags(@Tag(name = "Уведомления"))
public class NotificationController {
  private GetAllNotifications getAllNotifications;

  /**
   * Получает список уведомлений.
   *
   * @return Список уведомлений.
   */
  @GetMapping
  @Operation(summary = "Получить список уведомлений")
  @Async
  public CompletableFuture<Iterable<NotificationResponse>> getAll() {
    return Controllers.handleRequest(
        getAllNotifications,
        null,
        users -> users.stream()
            .map(NotificationResponse::new)
            .collect(Collectors.toList()));
  }
}
