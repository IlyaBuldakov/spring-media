package domain.entity.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
public class FileDto {
    /**
     *
     */
    @Getter private Integer id;

    /**
     *
     */
    @Getter private String name;

    /**
     *
     */
    @Getter private LocalDateTime dateCreate;

    /**
     *
     */
    @Getter private Format format;

    /**
     *
     */
    @Getter private String url;
}
