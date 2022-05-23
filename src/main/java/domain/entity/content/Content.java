package domain.entity.content;

import domain.entity.User;
import domain.entity.content.ContentFormat;
import domain.entity.task.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Контент
 */
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    /**
     * Идентификатор контента
     */
    @Getter private Integer id;

    /**
     * Тип контента
     */
    @Getter private ContentType contentType;

    /**
     * Название контента
     */
    @Getter private String name;

    /**
     * Дата создания
     */
    @Getter private LocalDateTime dateCreated;

    /**
     * Автор контента
     */
    @Getter private User author;

    /**
     * Тип контента
     */
    @Getter private ContentFormat format;

    /**
     * Путь к файлу
     */
    @Getter private Path url;

    /**
     * Путь к превью
     */
    @Getter private Path preview;

}
