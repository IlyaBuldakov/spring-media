package com.htc.infrastructure.models;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Entity;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import com.htc.infrastructure.exception.InvalidDataException;
import java.time.LocalDateTime;
import javax.persistence.Column;
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
@javax.persistence.Entity
@Table(name = "Tasks")
@AllArgsConstructor
public class TaskModel implements Entity.Model<Task> {
  /**
   * Идентификатор задачи.
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
  private Task.Status status;

  @Override
  public Task toEntity() throws InvalidDataException {
    final var id = Id
        .create(this.taskId)
        .getOrElseThrow(InvalidDataException::new);

    final var name = Task.Name
        .create(this.name)
        .getOrElseThrow(InvalidDataException::new);

    final var description = Task.Description
        .create(this.description)
        .getOrElseThrow(InvalidDataException::new);

    final var author = this.author.toEntity();
    final var executor = this.executor.toEntity();

    return new Task(id, name, contentType, description, author, executor, dateCreated,
        dateExpired, status);
  }

  protected TaskModel() {
  }

  public TaskModel(
      String name,
      Content.Type contentType,
      String description,
      UserModel author,
      UserModel executor,
      LocalDateTime dateCreated,
      LocalDateTime dateExpired,
      Task.Status status) {
    this(0, name, contentType, description, author, executor, dateCreated,
        dateExpired, status);
  }
}