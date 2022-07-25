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
  @Column(name = "id", unique = true)
  private Integer contentId;

  /**
   * Тип контента.
   */
  @Enumerated(EnumType.STRING)
  private Type type;

  /**
   * Название контента.
   */
  private String name;

  /**
   * Дата создания контента.
   */
  private LocalDateTime dateCreated;

  /**
   * Идентификатор автора контента.
   */
  private int authorId;

  /**
   * Формат контента.
   */
  @Enumerated(EnumType.STRING)
  private Format format;

  /**
   * Путь к файлу контента.
   */
  private String urlContent;

  /**
   * Путь к файлу превью контента.
   */
  private String urlPreview;

  /**
   * Идентификатор задачи содержащей контент.
   */
  private int taskId;

  /**
   * Подтверждение добавления контента в ленту.
   */
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
                   String urlPreview, int taskId) {
    this(Id.create(0).get(), type, name, dateCreated,
        authorId, format, urlContent, urlPreview, taskId);
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
                      String urlPreview, int taskId) {
    this.type = type;
    this.name = name;
    this.dateCreated = dateCreated;
    this.authorId = authorId;
    this.format = format;
    this.urlContent = urlContent;
    this.urlPreview = urlPreview;
    this.taskId = taskId;
  }
}