package finalproject.domain.entities.content;

import finalproject.domain.entities.user.User;
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
 * Сущность - единица контента.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
public class Content {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private ContentType type;

  @Column
  private @Getter String name;

  @Column
  private @Getter LocalDateTime dateCreated;

  @Column
  private @Getter User author;

  @Column
  private @Getter ContentFormat contentFormat;

  @Column
  private String preview;

  @Column
  private @Getter int task;

  @Column
  private @Getter String url;

  @Column
  private @Getter Boolean isPublished;

}
