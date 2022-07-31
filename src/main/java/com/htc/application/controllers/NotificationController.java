package com.htc.application.controllers;

import com.htc.application.dto.errors.BadRequest;
import com.htc.application.dto.errors.HttpError;
import com.htc.application.dto.notification.NotificationDto;
import com.htc.domain.entities.attributes.Id;
import com.htc.domain.service.NotificationService;
import com.htc.domain.usecases.BaseUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
  private NotificationService notificationService;

  /**
   * Возвращает список всех уведомлений.
   *
   * @return Список уведомлений.
   */
  @GetMapping
  @Operation(summary = "Получить список уведомлений")
  public ResponseEntity<Object> getAll(@AuthenticationPrincipal Id subjectId) {
    return this.notificationService
        .getAll(subjectId)
        .bimap(
            failure -> switch (failure) {
              case BaseUseCase.SubjectNotFound ignored ->
                  HttpError.forbidden("Аутентифицированный пользователь не найден.");
              case BaseUseCase.NoAccess ignored -> HttpError.forbidden("Недостаточно прав для выполнения операции.");
              default -> new BadRequest("Запрос содержит некорректные данные.");
            },
            notifications -> notifications
                .stream()
                .map(NotificationDto::new)
                .toList()
        )
        .fold(
            HttpError::toResponseEntity,
            ResponseEntity::ok
        );
  }
}
