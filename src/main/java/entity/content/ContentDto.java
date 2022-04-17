package entity.content;

import entity.users.UserBasicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
public class ContentDto {
    /**
     * Идентификатор контента
     */
    @Getter private Integer id;

    /**
     * Тип контента
     */
    @Getter private ContentTypeDto type;

    /**
     * Название контента
     */
    @Getter private String name;

    /**
     * Дата создание контента
     */
    @Getter private LocalDateTime dateCreated;

    /**
     * Автор контента
     */

    @Getter private UserBasicDto author;

    /**
     * Формат контента
     */
    @Getter private ContentFormat format;

    /**
     *  Путь к файлу
     */
    @Getter private String url;

    /**
     * Путь к превью
     */
    @Getter private String preview;
}
