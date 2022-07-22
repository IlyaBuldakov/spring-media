package finalproject.domain.entities.file;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс сущности Файл.
 */
@AllArgsConstructor
@Entity
@Getter
@Setter
public class File {

  /**
   * Идентификатор сущности. Обычно генерируется репозиторием.
   *
   * @return возвращает id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  /**
   * Название файла.
   *
   * @return возвращает name название документа.
   */
  @Column
  private String name;

  /**Дата создания документа.
   *
   * @return возвращает LocalDateTime dateCreated дату создания документа.
   */
  @Column
  private LocalDateTime dateCreated;

  /**
   * Формат файла.
   *
   * @return Возвращает Format формат документа.
   */
  @Column
  private Format format;

  /**
   * URL файла контента.
   *
   * @return Возвращает строку пути к файлу.
   */
  @Column
  private String url;

  /**
   * Идентификатор задачи.
   *
   * @return возвращает task идентификатор задачи.
   */
  @Column
  private int taskId;
}
