package domain.entities.content;

import domain.entities.user.UserBasic;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Класс, описывающий контент
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class Content {

    /**
     * Перечисление форматов контента
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
     * Идентификатор контента
     *
     * @return id Идентификатор контента
     */
    private @Getter int id;

    /**
     * Тип контента
     *
     * @return contentType Тип контента
     */
    private @Getter ContentType type;

    /**
     * Название контента
     *
     * @return name Название контента
     */
    private @Getter String name;

    /**
     * Дата создания
     *
     * @return dateCreated Дата создания
     */
    private @Getter LocalDate dateCreated;

    /**
     * Автор контента
     *
     * @return author Автор контента
     */
    private @Getter UserBasic author;

    /**
     * Формат контента {@link Format}
     *
     * @return format Формат контента
     */
    private @Getter Format format;

    /**
     * Путь к файлу
     *
     * @return url Путь к файлу
     */
    private @Getter String url;

    /**
     * Путь к превью
     *
     * @return preview Путь к превью
     */
    private @Getter String preview;
}
