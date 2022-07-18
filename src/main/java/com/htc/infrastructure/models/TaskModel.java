package com.htc.infrastructure.models;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.tasks.Task;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Модель задач для СУБД.
 */
@Entity
@Table(name = "Tasks")
@AllArgsConstructor
public class TaskModel implements Task {
  /**
   * Индентификатор задачи.
   */
  @javax.persistence.Id
  @GeneratedValue
  private Integer taskId;

  @Override
  public Id getId() {
    return Id.create(this.taskId).get();
  }

  /**
   * Название задачи.
   */
  private String name;

  @Override
  public Name getName() {
    return Task.Name.create(this.name).get();
  }

  /**
   * Требуемый задачей тип медиаконтента.
   */
  @Enumerated(EnumType.STRING)
  private @Getter Content.Type contentType;

  /**
   * Описание задачи.
   */
  private String description;

  @Override
  public Description getDescription() {
    return Task.Description.create(this.description).get();
  }

  /**
   * Автор задачи.
   */
  @Column(name = "author_id")
  @ManyToOne
  @JoinColumn(table = "users", name = "user_id", nullable = false)
  private @Getter UserModel author;

  /**
   * Исполнитель задачи.
   */
  @Column(name = "executor_id")
  @ManyToOne
  @JoinColumn(table = "users", name = "user_id", nullable = false)
  private @Getter UserModel executor;

  /**
   * Дата создания задачи.
   */
  private @Getter LocalDateTime dateCreated;

  /**
   * Дата окончания срока задачи.
   */
  private @Getter LocalDateTime dateExpired;

  /**
   * Статус задачи.
   */
  private @Getter Status status;

  protected TaskModel() {
  }

  public TaskModel(Id id, Name name, Content.Type contentType, Description description,
                   UserModel author, UserModel executor,
                   LocalDateTime dateCreated, LocalDateTime dateExpired,
                   Status status) {
    this.taskId = id.getValue();
    this.name = name.getValue();
    this.contentType = contentType;
    this.description = description.getValue();
    this.author = author;
    this.executor = executor;
    this.dateCreated = dateCreated;
    this.dateExpired = dateExpired;
    this.status = status;
  }

  public TaskModel(Name name, Content.Type contentType, Description description,
                   UserModel author, UserModel executor,
                   LocalDateTime dateCreated, LocalDateTime dateExpired,
                   Status status) {
    this(Id.create(0).get(), name, contentType, description, author, executor, dateCreated,
            dateExpired, status);
  }
}