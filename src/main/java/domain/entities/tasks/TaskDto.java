package domain.entities.tasks;

import domain.entities.comments.CommentDto;
import domain.entities.content.ContentDto;
import domain.entities.content.ContentTypeDto;
import domain.entities.files.FileDto;
import domain.entities.user.UserDto;

import java.time.LocalDateTime;

public class TaskDto {
    private int id;
    private String name;
    private ContentTypeDto contentType;
    private String description;
    private FileDto[] files;
    private UserDto author;
    private UserDto executor;
    private LocalDateTime dateCreated;
    private LocalDateTime dateExpired;
    private ContentDto[] contents;
    private CommentDto[] comments;
    private TaskStatusDto status;

}
