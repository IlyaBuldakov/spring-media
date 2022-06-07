package ru.kiryanovid.application.dto.content;

import ru.kiryanovid.application.dto.file.FileDto;
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
