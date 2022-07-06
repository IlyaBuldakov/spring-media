package finalproject.domain.entities.file;

import lombok.Getter;

import java.time.LocalDateTime;

public class File {

    /**
     * Возвращает @return id.
     */
    private @Getter int id;

    /**
     * Возвращает @return String name название документа.
     */
    private @Getter String name;

    /**
     * Возвращает @return LocalDateTime dateCreated дату создания документа.
     */
    private @Getter LocalDateTime dateCreated;

    /**
     * Возвращает @return Format формат документа.
     */
    private @Getter Format format;

    /**
     * Возвращает @return строку пути к файлу.
     */
    private @Getter String url;

  /**
     * Возвращает @return task идентификатор задачи.
     */
    private @Getter int task;
}
