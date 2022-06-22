package ru.kiryanovid.application.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.application.dto.content.ContentTypeDto;
import ru.kiryanovid.application.dto.users.UserBasicDto;
import ru.kiryanovid.domain.entity.task.Task;
import ru.kiryanovid.domain.entity.users.User;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
public class TaskListDto {
    /**
     *
     */
    @Getter private Integer id;

    /**
     *
     */
    @Getter private String name;

    /**
     *
     */
    @Getter private ContentTypeDto type;

    /**
     *
     */
    @Getter private UserBasicDto executor;

    /**
     *
     */
    @Getter private LocalDateTime dateExpired;

    /**
     *
     */
    @Getter private TaskStatusDto status;

    public TaskListDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.type = ContentTypeDto.mapToDto(task);
        this.executor = UserBasicDto.mapToDto(task.getExecutor());
        this.dateExpired = task.getDateExpired();
        this.status = TaskStatusDto.mapToDto(task);
    }

    public static TaskListDto mapToDto(Task task){
        return new TaskListDto(task);
    }

}

