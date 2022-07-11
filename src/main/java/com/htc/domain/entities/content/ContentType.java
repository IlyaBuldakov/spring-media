package com.htc.domain.entities.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс, описывающий тип контента.
 */
@AllArgsConstructor
public class ContentType {

    /**
     * Перечисление типов контента.
     */
    public enum Type {
        /**
         * Видео-контент.
         */
        VIDEO,

        /**
         * Аудио-контент.
         */
        AUDIO,

        /**
         * Фото-контент.
         */
        PHOTO
    }

    /**
     * Идентификатор типа контента.
     *
     * @return Идентификатор типа контента.
     */
    private @Getter int id;

    /**
     * Наименование (тип) контента.
     *
     * @return Наименование контента.
     */
    private @Getter Type name;
}
