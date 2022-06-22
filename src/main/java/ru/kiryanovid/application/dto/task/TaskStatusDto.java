package ru.kiryanovid.application.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.task.Status;
import ru.kiryanovid.domain.entity.task.Task;

/**
 *
 */
@AllArgsConstructor
public class TaskStatusDto {
    /**
     *
     */
    @Getter private Integer id;

    /**
     *
     */
    @Getter private String name;

    public TaskStatusDto(Task task){
        this.id = task.getStatus().getId();
        this.name = task.getStatus().getName();
    }

    public static TaskStatusDto mapToDto(Task task){
        return new TaskStatusDto(task);
    }
}
