package entity.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
public class TaskRequestDto {
    /**
     * Название задачи
     */
    @Getter private String name;

    /**
     * Тип контента
     */
    @Getter private Type type;

    /**
     * Описание задачи
     */
    @Getter private String description;

    /**
     * Автор задачи
     */
    @Getter private Integer author;

    /**
     * Исполнитель задачи
     */
    @Getter private Integer executor;

    /**
     * Дата выполнения
     */
    @Getter private LocalDateTime dateExpired;
}
