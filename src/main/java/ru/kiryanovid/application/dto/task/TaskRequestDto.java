package ru.kiryanovid.application.dto.task;

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
    @Getter private String type;

    /**
     * Описание задачи
     */
    @Getter private String description;

    /**
     * Идентификатор автора задачи
     */
    @Getter private Integer author;

    /**
     * Идентификатор исполнителя задачи
     */
    @Getter private Integer executor;

    /**
     * Дата выполнения
     */
    @Getter private LocalDateTime dateExpired;

    public TaskRequestDto() {
    }
}
