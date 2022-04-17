package domain.entities.notifications;

import domain.entities.tasks.TaskBasicDto;
import domain.entities.user.UserBasicDto;

import java.time.LocalDateTime;

public class NotificationDto {
    private int id;
    private NotificationType type;
    private LocalDateTime date;
    private String message;
    private UserBasicDto user;
    private TaskBasicDto task;
}
