package domain.entity.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class TaskBasicDto {
    /**
     * Идентификатор задания
     */
    @Getter private Integer id;

    /**
     * Название задания
     */
    @Getter private String name;
}
