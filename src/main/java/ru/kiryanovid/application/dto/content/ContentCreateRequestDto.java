package ru.kiryanovid.application.dto.content;

import ru.kiryanovid.domain.entity.file.FileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class ContentCreateRequestDto {
    /**
     * Файл
     */
    @Getter private FileDto file;

    /**
     * Идентификатор задачи
     */
    @Getter private Integer task;
}
