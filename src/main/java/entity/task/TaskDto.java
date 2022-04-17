package entity.task;

import entity.comment.CommentDto;
import entity.content.ContentDto;
import entity.content.ContentTypeDto;
import entity.users.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
public class TaskDto {
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
    @Getter private ContentTypeDto contentType;

    /**
     *
     */
    @Getter private String description;

    /**
     *
     */
    @Getter private File file;

    /**
     *
     */
    @Getter private UserDto author;

    /**
     *
     */
    @Getter private UserDto executor;

    /**
     *
     */
    @Getter private LocalDateTime dateCreated;

    /**
     *
     */
    @Getter private LocalDateTime dateExpired;

    /**
     *
     */
    @Getter private ContentDto contents;

    /**
     *
     */
    @Getter private CommentDto comments;

    /**
     *
     */
    @Getter private TaskStatusDto status;
}
