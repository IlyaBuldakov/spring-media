package ru.kiryanovid.application.dto.notification;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.application.dto.task.TaskBasicDto;
import ru.kiryanovid.application.dto.users.UserBasicDto;

/**
 * Представление нотификации.
 */
@AllArgsConstructor
public class NotificationDto {
  /**
  * Идентификатор.
  */
  @Getter private Integer id;

  /**
  * Тип.
  */
  @Getter private NotificationType type;

  /**
  * Дата.
  */
  @Getter private LocalDateTime date;

  /**
  * Сообщение.
  */
  @Getter private String message;

  /**
  * Пользователь.
  */
  @Getter private UserBasicDto user;

  /**
  * Задача.
  */
  @Getter private TaskBasicDto task;
}
