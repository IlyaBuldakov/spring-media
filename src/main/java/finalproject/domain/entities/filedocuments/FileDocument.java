package finalproject.domain.entities.filedocuments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finalproject.domain.entities.BaseEntity;
import finalproject.domain.entities.task.Task;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс сущности Файл.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FileDocument extends BaseEntity implements Serializable {

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
  @JsonIgnore
  @Getter
  @ManyToOne(targetEntity = Task.class)
  @JoinColumn(name = "taskId")
  private Task task;

  /**
   * Конструктор документа.
   *
   * @param name имя файла
   * @param format формат
   * @param url uri сохраненного файла
   * @param task задача
   */
  public FileDocument(String name, Format format, String url, Task task) {
    this.name = name;
    this.format = format;
    this.url = url;
    this.task = task;
  }

}
