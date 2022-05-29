package application.dto.task;

import application.dto.content.ContentTypeDto;
import domain.entity.users.UserBasicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

}

