package com.htc.application.dto.task;

import com.htc.application.dto.comment.CommentResponse;
import com.htc.application.dto.content.ContentResponse;
import com.htc.application.dto.file.FileResponse;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.task.TaskStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class TaskResponse {

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.type = task.getType();
        this.description = task.getDescription();
        this.files = FileResponse.createFromEntityList(task.getFiles());
        this.author = new UserResponse(task.getAuthor());
        this.executor = new UserResponse(task.getExecutor());
        this.dateCreated = task.getDateCreated();
        this.dateExpired = task.getDateExpired();
        this.contents = ContentResponse.createFromEntityList(task.getContents());
        this.comments = CommentResponse.createFromEntityList(task.getComments());
        this.status = task.getStatus();
    }

    private final @Getter int id;

    private final @Getter String name;

    private final @Getter ContentType type;

    private final @Getter String description;

    private final @Getter List<FileResponse> files;

    private final @Getter UserResponse author;

    private final @Getter UserResponse executor;

    private final @Getter LocalDate dateCreated;

    private final @Getter LocalDate dateExpired;

    private final @Getter List<ContentResponse> contents;

    private final @Getter List<CommentResponse> comments;

    private final @Getter TaskStatus status;
}
