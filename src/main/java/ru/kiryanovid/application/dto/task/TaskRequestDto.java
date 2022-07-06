package ru.kiryanovid.application.dto.task;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.application.dto.content.ContentsResponseDto;
import ru.kiryanovid.domain.entity.task.ContentType;

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
    @JsonAlias("type")
    @Getter private ContentType contentType;

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
