package finalproject.domain.entities.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Getter
@Setter
public class File {

    /**
     * Возвращает @return id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Возвращает @return String name название документа.
     */
    @Column
    private String name;

    /**
     * Возвращает @return LocalDateTime dateCreated дату создания документа.
     */
    @Column
    private LocalDateTime dateCreated;

    /**
     * Возвращает @return Format формат документа.
     */
    @Column
    private Format format;

    /**
     * Возвращает @return строку пути к файлу.
     */
    @Column
    private URL url;

  /**
     * Возвращает @return task идентификатор задачи.
     */
    @Column
    private int taskId;
}
