package finalproject.domain.entities.filedocuments;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finalproject.domain.entities.BaseEntity;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
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

  public FileDocument(String name, Format format, String url, Task task) {
    this.name = name;
    this.format = format;
    this.url = url;
    this.task = task;
  }

}
