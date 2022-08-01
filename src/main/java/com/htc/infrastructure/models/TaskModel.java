package com.htc.infrastructure.models;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Модель задачи для СУБД.
 */
@Entity
@Table(name = "Tasks")
public class TaskModel implements Task {
  /**
   * Идентификатор задачи.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  private Integer taskId;

  /**
   * Назваие задачи.
   */
  @Column(nullable = false)
  private String name;

  /**
   * Тип контента.
   */
  @Column(nullable = false)
  private Content.Type contentType;

  /**
   * Описание задачи.
   */
  @Column(nullable = false)
  private String description;

  /**
   * Массив идентификаторов файлов прикрепленных к задаче.
   */
  private int[] fileId;

  /**
   * Идентификатор автора задачи.
   */
  @Column(nullable = false)
  private int authorId;

  /**
   * Идентификатор исполнителя задачи.
   */
  @Column(nullable = false)
  private int executorId;

  /**
   * Дата создания задачи.
   */
  @Column(nullable = false)
  private LocalDateTime dateCreated;

  /**
   * Дата окончания задачи.
   */
  @Column(nullable = false)
  private LocalDateTime dateExpired;

  /**
   * Массив содержащий идентификаторы прикрепленного контента.
   */
  private int[] contentsId;

  /**
   * Массив содержащий идентификаторы комментариев в задаче.
   */
  private int[] commentsId;

  /**
   * Статус задачи.
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status;

  @Override
  public Id getId() {
    return Id.create(this.taskId).get();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Content.Type getContentType() {
    return this.contentType;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public int[] getFileId() {
    return this.fileId;
  }

  @Override
  public int getAuthorId() {
    return this.authorId;
  }

  @Override
  public int executorId() {
    return this.executorId;
  }

  @Override
  public LocalDateTime getDateCreated() {
    return this.dateCreated;
  }

  @Override
  public LocalDateTime getDateExpired() {
    return this.dateExpired;
  }

  @Override
  public int[] getContentsId() {
    return this.contentsId;
  }

  @Override
  public int[] getCommentsId() {
    return this.commentsId;
  }

  @Override
  public Status getStatus() {
    return this.status;
  }

  /**
   * Создает экземпляр класса {@link TaskModel}.
   */
  protected TaskModel() {}

  /**
   * Создает экземпляр класса {@link TaskModel}.
   *
   * @param name Название задачи.
   * @param contentType Тип контента задачи.
   * @param description Описание задачи.
   * @param authorId Идентификатор автора задачи.
   * @param executorId Идентификатор исполнителя задачи.
   * @param dateCreated Дата создания задачи.
   * @param dateExpired Дата окончания задачи.
   * @param status Статус задачи.
   */
  public TaskModel(
      String name, Content.Type contentType, String description,
      int authorId, int executorId, LocalDateTime dateExpired,
      LocalDateTime dateCreated, Status status) {
    this(Id.create(0).get(), name, contentType, description, authorId,
        executorId, dateCreated, dateExpired, status);
  }

  /**
   * Создает эклземпляр класса {@link TaskModel}.
   *
   * @param id Идентификатор задачи.
   * @param name Название задачи.
   * @param contentType Тип контента задачи.
   * @param description Описание задачи.
   * @param authorId Идентификатор автора задачи.
   * @param executorId Идентификатор исполнителя задачи.
   * @param dateCreated Дата создания задачи.
   * @param dateExpired Дата окончания задачи.
   * @param status Статус задачи.
   */
  public TaskModel(
      Id id, String name, Content.Type contentType,
      String description, int authorId,
      int executorId, LocalDateTime dateCreated,
      LocalDateTime dateExpired, Status status) {
    this.taskId = id.getValue();
    this.name = name;
    this.contentType = contentType;
    this.description = description;
    this.authorId = authorId;
    this.executorId = executorId;
    this.dateCreated = dateCreated;
    this.dateExpired = dateExpired;
    this.status = status;
  }
}
