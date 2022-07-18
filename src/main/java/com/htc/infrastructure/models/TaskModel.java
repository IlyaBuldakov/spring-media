package com.htc.infrastructure.models;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Task;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
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
  @Column(name = "id", unique = true)
  private Integer taskId;

  @Override
  public Id getId() {
    return Id.create(this.taskId).get();
  }

  /**
   * Назваие задачи.
   */
  private String name;

  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Тип контента.
   */
  private Content.Type contentType;

  @Override
  public Content.Type getContentType() {
    return this.contentType;
  }

  /**
   * Описание задачи.
   */
  private String description;

  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Массив идентификаторов файлов прикрепленных к задаче.
   */
  private int[] fileId;

  @Override
  public int[] getFileId() {
    return this.fileId;
  }

  /**
   * Идентификатор автора задачи.
   */
  private int authorId;

  @Override
  public int getAuthorId() {
    return this.authorId;
  }

  /**
   * Идентификатор исполнителя задачи.
   */
  private int executorId;

  @Override
  public int executorId() {
    return this.executorId;
  }

  /**
   * Дата создания задачи.
   */
  private LocalDateTime dateCreated;

  @Override
  public LocalDateTime getDateCreated() {
    return this.dateCreated;
  }

  /**
   * Дата окончания задачи.
   */
  private LocalDateTime dateExpired;

  @Override
  public LocalDateTime getDateExpired() {
    return this.dateExpired;
  }

  /**
   * Массив содержащий идентификаторы прикрепленного контента.
   */
  private int[] contentsId;

  @Override
  public int[] getContentsId() {
    return this.contentsId;
  }

  /**
   * Массив содержащий идентификаторы комментариев в задаче.
   */
  private int[] commentsId;

  @Override
  public int[] getCommentsId() {
    return this.commentsId;
  }

  /**
   * Статус задачи.
   */
  private Status status;

  @Override
  public Status getStatus() {
    return this.status;
  }

  protected TaskModel() {}

  public TaskModel(
      String name, Content.Type contentType, String description, int[] fileId,
      int authorId, int executorId, LocalDateTime dateCreated, LocalDateTime dateExpired,
      int[] contentsId, int[] commentsId, Status status) {
    this(
        Id.create(0).get(), name, contentType, description, fileId, authorId,
        executorId, dateCreated, dateExpired, contentsId, commentsId, status);
  }

  /**
   * Создает эклземпляр класса {@Link TaskModel}.
   *
   * @param id Идентификатор задачи.
   * @param name Название задачи.
   * @param contentType Тип контента задачи.
   * @param description Описание задачи.
   * @param fileId Идентификаторы прикрепленных файло.
   * @param authorId Идентификатор автора задачи.
   * @param executorId Идентификатор исполнителя задачи.
   * @param dateCreated Дата создания задачи.
   * @param dateExpired Дата окончания задачи.
   * @param contentsId Идентификаторы прикрепленного контента к задаче.
   * @param commentsId Идентификаторы комментариев задачи.
   * @param status Статус задачи.
   */
  public TaskModel(
      Id id, String name, Content.Type contentType,
      String description, int[] fileId, int authorId,
      int executorId, LocalDateTime dateCreated,
      LocalDateTime dateExpired, int[] contentsId,
      int[] commentsId, Status status) {
    this.taskId = id.getValue();
    this.name = name;
    this.contentType = contentType;
    this.description = description;
    this.fileId = fileId;
    this.authorId = authorId;
    this.executorId = executorId;
    this.dateCreated = dateCreated;
    this.dateExpired = dateExpired;
    this.contentsId = contentsId;
    this.commentsId = commentsId;
    this.status = status;
  }
}
