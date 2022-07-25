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
  @Column(nullable = false)
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
  @Column(nullable = false)
  private int[] contentsId;

  /**
   * Массив содержащий идентификаторы комментариев в задаче.
   */
  @Column(nullable = false)
  private int[] commentsId;

  /**
   * Статус задачи.
   */
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
      String name, Content.Type contentType, String description, int[] fileId,
      int authorId, int executorId, LocalDateTime dateCreated, LocalDateTime dateExpired,
      int[] contentsId, int[] commentsId, Status status) {
    this(Id.create(0).get(), name, contentType, description, fileId, authorId,
        executorId, dateCreated, dateExpired, contentsId, commentsId, status);
  }

  /**
   * Создает эклземпляр класса {@link TaskModel}.
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
