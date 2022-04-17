package entity.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

/**
 *
 */
@AllArgsConstructor
public class FileRequestDto {
    /**
     *  Файл
     */
    @Getter private FileDto file;

    /**
     * Идентификатор задачи
     */
    @Getter private Integer id;
}
