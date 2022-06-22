package ru.kiryanovid.application.dto.content;

import ru.kiryanovid.application.dto.task.TaskStatusDto;
import ru.kiryanovid.domain.entity.task.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.task.Task;

/**
 *
 */
@AllArgsConstructor
public class ContentTypeDto {
    /**
     *
     */
    @Getter private Integer id;

    /**
     *
     */
    @Getter private String name;

    ContentTypeDto(Task task){
        this.id = task.getContentType().getId();
        this.name = task.getContentType().getName();
    }
    public static ContentTypeDto mapToDto(Task task){
        return new ContentTypeDto(task);
    }
}
