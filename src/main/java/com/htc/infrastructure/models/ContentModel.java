package com.htc.infrastructure.models;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.attributes.Id;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
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

  @Override
  public Id getId() {
    return Id.create(this.contentId).get();
  }

  /**
   * Тип контента.
   */
  private Type type;

  @Override
  public Type getType() {
    return this.type;
  }

  /**
   * Название контента.
   */
  private String name;

  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Дата создания контента.
   */
  private LocalDateTime dateCreated;

  @Override
  public LocalDateTime getDateCreated() {
    return this.dateCreated;
  }

  /**
   * Идентификатор автора контента.
   */
  private int authorId;

  @Override
  public int getAuthorId() {
    return this.authorId;
  }

  /**
   * Формат контента.
   */
  private Format format;

  @Override
  public Format getFormat() {
    return this.format;
  }

  /**
   * Путь к файлу контента.
   */
  private String urlContent;

  @Override
  public String getUrlContent() {
    return this.urlContent;
  }

  /**
   * Путь к файлу превью контента.
   */
  private String urlPreview;

  @Override
  public String getUrlPreview() {
    return this.urlPreview;
  }

  /**
   * Идентификатор задачи содержащей контент.
   */
  private int taskId;

  @Override
  public int getTaskId() {
    return this.taskId;
  }
}
