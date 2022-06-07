package ru.kiryanovid.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.file.Format;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
public class File { /**
 *
 */
@Getter
private Integer id;

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
