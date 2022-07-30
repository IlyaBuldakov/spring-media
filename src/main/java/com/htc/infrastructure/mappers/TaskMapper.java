package com.htc.infrastructure.mappers;

import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.task.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Представление сущности задачи для БД.
 */
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class TaskMapper implements Task {

  /**
   * Конструктор для обновления.
   *
   * @param id          Идентификатор задачи.
   * @param name        Имя задачи.
   * @param type        Тип задачи.
   * @param description Описание задачи.
   * @param author      Автор задачи.
   * @param executor    Исполнитель задачи.
   * @param dateExpired Дата исполнения.
   */
  public TaskMapper(int id, String name, ContentType type, String description,
                    int author, int executor, LocalDate dateExpired) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.description = description;
    this.authorId = author;
    this.executorId = executor;
    this.dateExpired = dateExpired;
    this.status = TaskStatus.IN_WORK;
  }

  /**
   * Конструктор без идентификатора.
   *
   * @param name        Имя задачи.
   * @param type        Тип задачи.
   * @param description Описание задачи.
   * @param author      Автор задачи.
   * @param executor    Исполнитель задачи.
   * @param dateExpired Дата исполнения.
   */
  public TaskMapper(String name, ContentType type, String description,
                    int author, int executor, LocalDate dateExpired) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.authorId = author;
    this.executorId = executor;
    this.dateCreated = LocalDate.now();
    this.dateExpired = dateExpired;
    this.status = TaskStatus.IN_WORK;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  public @Getter Integer id;

  private @Getter String name;

  @Enumerated(EnumType.STRING)

  private @Getter ContentType type;

  private @Getter String description;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "task_id")
  @LazyCollection(LazyCollectionOption.FALSE)
  private @Getter List<FileMapper> files;

  @ManyToOne
  @JoinColumn(name = "author_id", insertable = false, updatable = false)
  private @Getter UserMapper author;

  @Column(name = "author_id")
  private @Getter Integer authorId;

  @ManyToOne
  @JoinColumn(name = "executor_id", insertable = false, updatable = false)
  private @Getter UserMapper executor;

  @Column(name = "executor_id")
  private @Getter Integer executorId;

  @Column(name = "date_created")
  private @Getter LocalDate dateCreated;

  @Column(name = "date_expired")
  private @Getter LocalDate dateExpired;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "task_id")
  @LazyCollection(LazyCollectionOption.FALSE)
  private @Getter List<ContentMapper> contents;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "task_id")
  @LazyCollection(LazyCollectionOption.FALSE)
  private @Getter List<CommentMapper> comments;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private @Getter TaskStatus status;
}
