package domain.entities.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс, описывающий тип контента
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class ContentType {

    /**
     * Перечисление типов контента
     */
    public enum Type {
        /**
         * Видео-контент
         */
        VIDEO,

        /**
         * Аудио-контент
         */
        AUDIO,

        /**
         * Фото-контент
         */
        PHOTO
    }

    /**
     * Идентификатор типа контента
     *
     * @return id Идентификатор типа контента
     */
    private @Getter int id;

    /**
     * Тип контента
     *
     * @return type Тип контента
     */
    private @Getter Type type;
}
