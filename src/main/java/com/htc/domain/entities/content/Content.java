package com.htc.domain.entities.content;

import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Класс, описывающий контент.
 */
@AllArgsConstructor
public class Content {

    /**
     * Перечисление форматов контента.
     */
    public enum Format {
        JPG,

        PNG,

        MP3,

        M4A,

        FLAC,

        AVI,

        MP4
    }


    /**
     * Идентификатор контента.
     *
     * @return Идентификатор контента.
     */
    private @Getter int id;

    /**
     * Тип контента.
     *
     * @return Тип контента.
     */
    private @Getter ContentType type;

    /**
     * Название контента.
     *
     * @return Название контента.
     */
    private @Getter String name;

    /**
     * Дата создания.
     *
     * @return Дата создания.
     */
    private @Getter LocalDate dateCreated;

    /**
     * Автор контента.
     *
     * @return Автор контента.
     */
    private @Getter User author;

    /**
     * Формат контента {@link Format}.
     *
     * @return Формат контента.
     */
    private @Getter Format format;

    /**
     * Путь к файлу.
     *
     * @return Путь к файлу.
     */
    private @Getter String url;

    /**
     * Путь к превью.
     *
     * @return Путь к превью.
     */
    private @Getter String preview;
}
