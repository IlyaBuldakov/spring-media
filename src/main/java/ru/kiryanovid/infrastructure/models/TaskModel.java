package ru.kiryanovid.infrastructure.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
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
  @Column(unique = true, nullable = false)
  @Getter
  @Setter
  private Integer id;

  /**
  * Название задачи.
  */
  @Column(nullable = false)
  @Getter
  @Setter
  private String name;

  /**
  * Тип контента в задаче.
  */
  @Enumerated(EnumType.STRING)
  @Getter
  @Setter
  private ContentType contentType;

  /**
  * Описание задачи.
  */
  @Getter
  @Setter
  private String description;

  /**
  * Путь к файлу контента.
  */
  @Transient
  @Getter
  @Setter
  private File file;

  /**
  * Автор задачи.
  */
  @ManyToOne
  @Getter
  @Setter
  private UserModel author;

  /**
  * Исполнитель задачи.
  */
  @ManyToOne
  @Getter
  @Setter
  private UserModel executor;

  /**
  * Дата создания задачи.
  */
  @Column(nullable = false)
  @Getter
  @Setter
  private LocalDateTime dateCreate;

  /**
  * Дата выполнения задачи.
  */
  @Column(nullable = false)
  @Getter
  @Setter
  private LocalDateTime dateExpired;

  /**
  * Контент.
  */
  @Transient
  @Getter
  @Setter
  private Content content;

  /**
  * Комментарий.
  */
  @Transient
  @Getter
  @Setter
  private Comment comment;

  /**
  * Статус задачи.
  */
  @Getter
  @Setter
  private Status status;

  protected TaskModel() {
  }
}
