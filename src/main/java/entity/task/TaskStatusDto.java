package entity.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    @Getter private Status name;
}
