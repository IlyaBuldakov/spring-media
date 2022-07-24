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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
  @Column(name = "id")
  private Integer taskId;

  /**
   * Название задачи.
   */
  @NotNull
  private String name;

  /**
   * Требуемый задачей тип медиаконтента.
   */
  @NotNull
  @Enumerated(EnumType.STRING)
  private @Getter Content.Type contentType;

  /**
   * Описание задачи.
   */
  @NotNull
  private String description;

  /**
   * Автор задачи.
   */
  @NotNull
  @ManyToOne
  private @Getter UserModel author;

  /**
   * Исполнитель задачи.
   */
  @NotNull
  @ManyToOne
  private @Getter UserModel executor;

  /**
   * Дата создания задачи.
   */
  @NotNull
  private @Getter LocalDateTime dateCreated;

  /**
   * Дата окончания срока задачи.
   */
  @NotNull
  private @Getter LocalDateTime dateExpired;

  /**
   * Статус задачи.
   */
  @NotNull
  @Enumerated(EnumType.STRING)
  private @Getter Status status;

  @Override
  public Id getId() {
    return Id.create(this.taskId).get();
  }

  @Override
  public Name getName() {
    return Task.Name.create(this.name).get();
  }

  @Override
  public Description getDescription() {
    return Task.Description.create(this.description).get();
  }

  protected TaskModel() {
  }

  /**
   * Создает модель задачи.
   *
   * @param id Индентификатор задачи.
   * @param name Название задачи.
   * @param contentType Тип контента.
   * @param description Описание задачи.
   * @param author Пользователь - автор задачи.
   * @param executor Пользователь - исполнитель задачи.
   * @param dateCreated Дата создания задачи.
   * @param dateExpired Дата окончания срока выполнеия задачи.
   * @param status Статус задачи.
   */
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