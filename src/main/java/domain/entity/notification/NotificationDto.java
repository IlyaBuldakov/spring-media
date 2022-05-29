package domain.entity.notification;

import application.dto.task.TaskBasicDto;
import domain.entity.users.UserBasicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
public class NotificationDto {
    /**
     *
     */
    @Getter private Integer id;

    /**
     *
     */
    @Getter private NotificationType type;

    /**
     *
     */
    @Getter private LocalDateTime date;

    /**
     *
     */
    @Getter private String message;

    /**
     *
     */
    @Getter private UserBasicDto user;

    /**
     *
     */
    @Getter private TaskBasicDto task;
}
