package ru.kiryanovid.application.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.application.dto.comments.CommentDto;
import ru.kiryanovid.application.dto.content.ContentDto;
import ru.kiryanovid.application.dto.content.ContentTypeDto;
import ru.kiryanovid.application.dto.files.FileDto;
import ru.kiryanovid.application.dto.users.UserDto;
import ru.kiryanovid.domain.entity.task.Task;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
public class TaskDto {
    /**
     *
     */
    @Getter
    private Integer id;

    /**
     *
     */
    @Getter
    private String name;

    /**
     *
     */
    @Getter
    private ContentTypeDto contentType;

    /**
     *
     */
    @Getter
    private String description;

    /**
     *
     */
    @Getter
    private FileDto[] files;

    /**
     *
     */
    @Getter
    private UserDto author;

    /**
     *
     */
    @Getter
    private UserDto executor;

    /**
     *
     */
    @Getter
    private LocalDateTime dateCreated;

    /**
     *
     */
    @Getter
    private LocalDateTime dateExpired;

    /**
     *
     */
    @Getter
    private ContentDto[] contents;

    /**
     *
     */
    @Getter
    private CommentDto[] comments;

    /**
     *
     */
    @Getter
    private TaskStatusDto status;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.contentType = ContentTypeDto.mapToDto(task);
        this.description = task.getDescription();
        this.files = null;
        this.author = new UserDto(task.getAuthor());
        this.executor = new UserDto(task.getExecutor());
        this.dateCreated = task.getDateCreate();
        this.dateExpired = task.getDateExpired();
        this.contents = null;
        this.comments = null;
        this.status = TaskStatusDto.mapToDto(task);
    }
}
