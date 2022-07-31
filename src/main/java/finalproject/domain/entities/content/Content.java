package finalproject.domain.entities.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finalproject.domain.entities.BaseEntity;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.*;

/**
 * Сущность - единица контента.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Content extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private ContentType type;

  @Column
  private @Getter String name;

  @Getter
  @Setter
  @Column
  private LocalDateTime dateCreated;

  @Column
  private @Getter ContentFormat contentFormat;

  @Column
  private String preview;

  @JsonIgnore
  @Getter
  @ManyToOne(targetEntity = Task.class)
  @JoinColumn(name = "taskId")
  private Task task;

  @Column
  private @Getter String url;

  @Column
  private @Getter Boolean isPublished;

  public Content(ContentType type, String name, ContentFormat contentFormat,
                 String preview, Task task, String url, Boolean isPublished) {
    this.type = type;
    this.name = name;
    this.contentFormat = contentFormat;
    this.preview = preview;
    this.task = task;
    this.url = url;
    this.isPublished = isPublished;
  }


}
