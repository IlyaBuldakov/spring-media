package com.htc.domain.entities.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс, описывающий тип контента.
 */
@AllArgsConstructor
public enum ContentType {

        /**
         * Видео-контент.
         */
        VIDEO(1, "Видео"),

        /**
         * Аудио-контент.
         */
        AUDIO(2, "Аудио"),

        /**
         * Фото-контент.
         */
        PHOTO(3, "Фото");

    /**
     * Идентификатор типа контента.
     */
    private final @Getter int id;

    /**
     * Наименование контента.
     */
    private final @Getter String name;
}
