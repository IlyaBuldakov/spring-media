package com.htc.application.controllers;

import com.htc.application.dto.notification.NotificationDto;
import com.htc.domain.usecases.notification.GetAllNotificationsByUser;
import com.htc.utility.Controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с уведомлениями.
 */
@RestController
@RequestMapping(path = "api/notification")
@AllArgsConstructor
@SecurityRequirement(name = "JWT")
@Tags(@Tag(name = "Уведомления"))
public class NotificationController {
  private GetAllNotificationsByUser getAllNotifications;

  /**
   * Возвращает список всех уведомлений.
   *
   * @return Список уведомлений.
   */
  @GetMapping
  @Operation(summary = "Получить список уведомлений")
  public CompletableFuture<Collection<NotificationDto>> getAll() {
    return Controllers.handleRequest(
            getAllNotifications,
            null,
            notification -> notification.stream()
                    .map(NotificationDto::new)
                    .collect(Collectors.toList()));
  }
}
