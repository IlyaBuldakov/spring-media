package ru.kiryanovid.application.dto.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 *
 */
@AllArgsConstructor
public class ContentsResponseDto {
    /**
     * Список контента в ответе
     */
    @Getter private List<ContentDto> contents;

    /**
     * Количество контента в ответе
     */
    @Getter private Integer total;
}
