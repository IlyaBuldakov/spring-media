package entity.notification;

import entity.task.TaskBasicDto;
import entity.users.UserBasicDto;
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
