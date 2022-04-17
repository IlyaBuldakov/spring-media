package domain.entities.tasks;

import domain.entities.content.ContentTypeDto;
import domain.entities.user.UserBasicDto;

import java.time.LocalDateTime;

public class TaskListDto {
    private int id;
    private String name;
    private ContentTypeDto type;
    private UserBasicDto executor;
    private LocalDateTime dateExpired;
    private TaskStatusDto status;
}
