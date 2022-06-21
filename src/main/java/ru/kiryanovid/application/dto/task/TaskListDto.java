package ru.kiryanovid.application.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.application.dto.users.UserBasicDto;
import ru.kiryanovid.domain.entity.task.ContentType;
import ru.kiryanovid.domain.entity.task.Status;
import ru.kiryanovid.domain.entity.task.Task;

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
    @Getter private ContentType type;

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
    @Getter private Status status;

    public TaskListDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.type = task.getContentType();
        this.executor = null;
        this.dateExpired = task.getDateExpired();
        this.status = task.getStatus();
    }

    public static TaskListDto map(Task task){
        return new TaskListDto(task);
    }

}

