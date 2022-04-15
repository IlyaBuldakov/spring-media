package domain.entities.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Упрощённое (базовое) представление задачи
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class TaskBasic {

    /**
     * Идентификатор задачи
     *
     * @return id Идентификатор задачи
     */
    private @Getter int id;

    /**
     * Название задачи
     *
     * @return name Название задачи
     */
    private @Getter String name;
}
