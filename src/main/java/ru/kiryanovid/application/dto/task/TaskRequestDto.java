package ru.kiryanovid.application.dto.task;

import ru.kiryanovid.domain.entity.task.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.users.User;

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
    @Getter private ContentType contentType;

    /**
     * Описание задачи
     */
    @Getter private String description;

    /**
     * Автор задачи
     */
    @Getter private User author;

    /**
     * Исполнитель задачи
     */
    @Getter private User executor;

    /**
     * Дата выполнения
     */
    @Getter private LocalDateTime dateExpired;
}
