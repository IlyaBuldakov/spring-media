package com.htc.infrastructure.models;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Модель контента для СУБД.
 */
@Entity
@Table(name = "Contents")
public class ContentModel implements Content {
  /**
   * Идентификатор контента.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  private Integer contentId;

  /**
   * Тип контента.
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Type type;

  /**
   * Название контента.
   */
  @Column(nullable = false)
  private String name;

  /**
   * Дата создания контента.
   */
  @Column(nullable = false)
  private LocalDateTime dateCreated;

  /**
   * Идентификатор автора контента.
   */
  @Column(nullable = false)
  private int authorId;

  /**
   * Формат контента.
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Format format;

  /**
   * Путь к файлу контента.
   */
  @Column(nullable = false)
  private String urlContent;

  /**
   * Путь к файлу превью контента.
   */
  @Column(nullable = false)
  private String urlPreview;

  /**
   * Идентификатор задачи содержащей контент.
   */
  @Column(nullable = false)
  private int taskId;

  /**
   * Подтверждение добавления контента в ленту.
   */
  @Column(nullable = false)
  private boolean approve;

  @Override
  public Id getId() {
    return Id.create(this.contentId).get();
  }

  @Override
  public Type getType() {
    return this.type;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public LocalDateTime getDateCreated() {
    return this.dateCreated;
  }

  @Override
  public int getAuthorId() {
    return this.authorId;
  }

  @Override
  public Format getFormat() {
    return this.format;
  }

  @Override
  public String getUrlContent() {
    return this.urlContent;
  }

  @Override
  public String getUrlPreview() {
    return this.urlPreview;
  }

  @Override
  public int getTaskId() {
    return this.taskId;
  }

  @Override
  public boolean getApprove() {
    return this.approve;
  }

  /**
   * Создаёт экземпляр класса {@link ContentModel}.
   */
  protected ContentModel() {}

  /**
   * Создаёт экземпляр класса {@link FileModel}.
   *
   * @param type Тип контента.
   * @param name Название контента.
   * @param dateCreated Дата создания контента.
   * @param authorId Идентификатор автора контента.
   * @param format Формат контента.
   * @param urlContent Путь контента.
   * @param urlPreview Путь превью контента.
   * @param taskId Идентификатор задачи содержащей контент.
   */
  public ContentModel(Content.Type type, String name, LocalDateTime dateCreated,
                   int authorId, Content.Format format, String urlContent,
                   String urlPreview, int taskId, boolean approve) {
    this(Id.create(0).get(), type, name, dateCreated,
        authorId, format, urlContent, urlPreview, taskId, approve);
  }

  /**
   * Создает экземпляр класса {@Link ContentModel}.
   *
   * @param id Идентификатор контента.
   * @param type Тип контента.
   * @param name Название контента.
   * @param dateCreated Дата создания контента.
   * @param authorId Идентификатор автора контента.
   * @param format Формат контента.
   * @param urlContent Путь контента.
   * @param urlPreview Путь превью контента.
   * @param taskId Идентификатор задачи содержащей контент.
   */
  public ContentModel(Id id, Content.Type type, String name,
                      LocalDateTime dateCreated, int authorId,
                      Content.Format format, String urlContent,
                      String urlPreview, int taskId, boolean approve) {
    this.type = type;
    this.name = name;
    this.dateCreated = dateCreated;
    this.authorId = authorId;
    this.format = format;
    this.urlContent = urlContent;
    this.urlPreview = urlPreview;
    this.taskId = taskId;
    this.approve = approve;
  }
}
