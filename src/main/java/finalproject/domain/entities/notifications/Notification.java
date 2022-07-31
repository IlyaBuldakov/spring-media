package finalproject.domain.entities.notifications;

import finalproject.domain.entities.BaseEntity;
import finalproject.domain.entities.task.Task;
import finalproject.domain.entities.user.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность - уведомление.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notification extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @Column
  private NotificationType type;

  @Column
  private LocalDateTime date;

  private String message;

  @Column
  private int userId;

  private String modifierName;


  private String taskName;

  private String userName;

  @Column
  private int taskId;

  @Column
  private Note note;

  /**
   * Перечисление типов изменений в задачах.
   */
  public enum Note {
    ADD("добавление"),
    DELETE("удаление"),
    APPROVED("одобрение контента");

    @Getter
    public final String modification;

    Note(String modification) {
      this.modification = modification;

    }

  }

  /**
   * Генерация сообщения.
   *
   * @return сообщение
   */
  public String createMessage() {
    return "В задаче " + this.taskName
            + " пользователь " + this.modifierName + " произвел "
            + this.note.getModification() + " " + this.type.getToMessage();
  }

  /**
   * Конструктор уведомления.
   *
   * @param type тип уведомления
   * @param date дата
   * @param modifyer пользователь, осуществивший изменения
   * @param task задача
   * @param note тип изменения
   */
  public Notification(NotificationType type, LocalDateTime date,
                      User modifyer, Task task, Note note) {
    this.type = type;
    this.date = date;
    this.userId = modifyer.getId();
    this.userName = modifyer.getName();
    this.taskId = task.getId();
    this.taskName = task.getName();
    this.modifierName = modifyer.getName();
    this.note = note;
    this.message = createMessage();
  }

}
