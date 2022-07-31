package finalproject.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.User;
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
 * Сущность - комментарий.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JsonIgnore
  @Getter
  @ManyToOne(targetEntity = Task.class)
  @JoinColumn(name = "taskId")
  private Task task;

  @Column
  private LocalDateTime date;

  @JsonIgnore
  @Getter
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "userId")
  private User user;

  @Column
  private String message;

  /**
   * Конструктор комментария.
   *
   * @param task задача
   * @param user пользователь
   * @param message текст комментария
   * @param date дата создания
   */
  public Comment(Task task,  User user, String message, LocalDateTime date) {
    this.task = task;
    this.date = date;
    this.user = user;
    this.message = message;
  }
}
