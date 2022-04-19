package domain.entities.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Задача - базовый класс
 */

@NoArgsConstructor
@AllArgsConstructor
public class TaskBasicDto {

    /**
     * @return id идентификатор задачи
     */
    private @Getter int id;

    /**
     * @return name Название задачи
     */
    private @Getter String name;


}
