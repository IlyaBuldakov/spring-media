package domain.entities.tasks;

import domain.entities.content.ContentTypeDto;

import java.time.LocalDateTime;

public class TaskRequestDto {
    private String name;
    private ContentTypeDto type;
    private String description;
    private int authorId;
    private int executorId;
    private LocalDateTime dateExpired;
}
