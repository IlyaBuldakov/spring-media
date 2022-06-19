package ru.kiryanovid.application.dto.content;

import ru.kiryanovid.application.dto.files.FileDto;
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
