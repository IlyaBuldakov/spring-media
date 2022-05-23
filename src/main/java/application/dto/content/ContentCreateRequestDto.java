package application.dto.content;

import domain.entity.file.FileDto;
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
