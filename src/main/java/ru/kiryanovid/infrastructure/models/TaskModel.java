package ru.kiryanovid.infrastructure.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.kiryanovid.domain.entity.comment.Comment;
import ru.kiryanovid.domain.entity.content.Content;
import ru.kiryanovid.domain.entity.file.File;
import ru.kiryanovid.domain.entity.task.ContentType;
import ru.kiryanovid.domain.entity.task.Status;


/**
 * Модель задачи для СУБД.
 */
@AllArgsConstructor()
@Entity
@Table(name = "tasks")
public class TaskModel {
  /**
  * Идентификатор задачи.
  */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter @Setter private Integer id;

  /**
  * Название задачи.
  */
  @Getter @Setter private String name;

  /**
  * Тип контента в задаче.
  */
  @Enumerated(EnumType.STRING)
  @Getter @Setter private ContentType contentType;

  /**
  * Описание задачи.
  */
  @Getter @Setter private String description;

  /**
  * Путь к файлу контента.
  */
  @Transient
  @Getter @Setter private File file;

  /**
  * Автор задачи.
  */
  @ManyToOne
  private @Getter @Setter UserModel author;

  /**
  * Исполнитель задачи.
  */
  @ManyToOne
  private @Getter @Setter UserModel executor;

  /**
  * Дата создания задачи.
  */
  private @Getter @Setter LocalDateTime dateCreate;

  /**
  * Дата выполнения задачи.
  */
  private @Getter @Setter LocalDateTime dateExpired;

  /**
  * Контент.
  */
  @Transient
  private @Getter @Setter Content content;

  /**
  * Комментарий.
  */
  @Transient
  private @Getter @Setter Comment comment;

  /**
  * Статус задачи.
  */
  private @Getter @Setter Status status;

  protected TaskModel() {
  }
}
