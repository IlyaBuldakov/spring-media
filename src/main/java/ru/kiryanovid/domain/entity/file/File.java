package ru.kiryanovid.domain.entity.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class File {
    /**
     *
     */
    @Getter
    private Integer id;

    /**
     *
     */
    @Getter
    private String name;

    /**
     *
     */
    @Getter
    private LocalDateTime dateCreate;

    /**
     *
     */
    @Getter
    private Format format;

    /**
     *
     */
    @Getter
    private String url;

}