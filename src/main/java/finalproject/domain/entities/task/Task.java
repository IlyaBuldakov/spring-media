package finalproject.domain.entities.task;

import finalproject.domain.entities.Comment;
import finalproject.domain.entities.content.Content;
import finalproject.domain.entities.content.ContentType;
import finalproject.domain.entities.file.File;
import finalproject.domain.entities.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

public class Task {
  /**
   * Возвращает @return id идентификатор задачи.
   */
  @Id
  @GeneratedValue
  private @Getter int id;

  /**
   * Возвращает @return name название задачи.
   */
  @Getter
  @Setter
  private String name;

  /**
   * Возвращает @return description описание задачи.
   */
  @Getter
  @Setter
  private String description;

  /**
   * Возвращает @return автора задачи.
   */
  private @Getter User author;

  /**
   * Возвращает @return исполнителя задачи.
   */
  @Getter
  @Setter
  private User contentMaker;

  /**
   * Возвращает @return менеджера задачи.
   */
  @Getter
  @Setter
  private User manager;

  /**
   * Возвращает @return LocalDateTime дату создания задачи.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   * Возвращает @return LocalDateTime дату выполнения задачи.
   */
  @Getter
  @Setter
  private LocalDateTime dateExpired;

  /**
   * Возвращает @return type тип контента.
   */
  private @Getter ContentType type;

  /**
   * Возвращает @return TaskStatusDto status статус задачи.
   */
  @Getter
  @Setter
  private TaskStatus taskStatus;

}
