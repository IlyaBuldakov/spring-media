package finalproject.application.controllers;

import finalproject.application.dto.content.ContentsResponseDto;
import finalproject.application.dto.notifications.NotificationDto;
import finalproject.application.dto.task.TaskBasicDto;
import finalproject.application.dto.user.UserBasicDto;
import finalproject.application.services.AuthService;
import finalproject.application.services.NotificationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер уведомлений.
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/notifications")
public class NotificationContoller {

  NotificationService notificationService;
  AuthService authService;

  /**
   * Получение уведомлений.
   *
   * @return ленту уведомлений
   */
  @ApiOperation(value = "", authorizations = { @Authorization(value = "Bearer") })
  @GetMapping
  public NotificationDto[] getNotifications() {
    int autorizedUserId = authService.getId();
    return notificationService.getNotifications(autorizedUserId)
            .stream().map(note -> new NotificationDto(
            note.getId(), NotificationDto.Type.valueOf(note.getType().name()),
            note.getDate().toString(), note.getMessage(),
                new UserBasicDto(note.getUserId(), note.getUserName()),
                new TaskBasicDto(note.getTaskId(), note.getTaskName())))
            .toList().toArray(new NotificationDto[0]);


  }

}
