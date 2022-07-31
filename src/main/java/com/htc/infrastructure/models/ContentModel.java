package com.htc.infrastructure.models;

import com.htc.domain.entities.Content;
import com.htc.domain.entities.Entity;
import com.htc.domain.entities.attributes.Id;
import com.htc.infrastructure.exception.InvalidDataException;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

/**
 * Модель медиаконтента для СУБД.
 */
@javax.persistence.Entity
@Table(name = "Contents")
@AllArgsConstructor
public class ContentModel implements Entity.Model<Content> {

  /**
   * Идентификатор медиаконтента.
   */
  @javax.persistence.Id
  @GeneratedValue
  @Column(name = "id")
  private Integer contentId;

  /**
   * Задача изменение которой отображается в уведомлении.
   */
  @ManyToOne
  @NotNull
  private TaskModel parentTask;

  /**
   * Тип медиаконтента.
   */
  @NotNull
  private Content.Type type;

  /**
   * Название медиаконтента.
   */
  @NotNull
  private String name;

  /**
   * Дата создания медиаконтента.
   */
  @NotNull
  private LocalDateTime dateCreated;

  /**
   * Автор медиаконтента.
   */
  @NotNull
  @ManyToOne
  private UserModel author;

  /**
   * Формат медиаконтента.
   */
  @NotNull
  private Content.Format format;

  /**
   * Адрес медиаконтента.
   */
  @NotNull
  @Column(unique = true)
  private String url;

  /**
   * Адрес превью медиаконтента.
   */
  @NotNull
  private String preview;

  protected ContentModel() {
  }

  @Override
  public Content toEntity() {
    final var id = Id
        .create(this.contentId)
        .getOrElseThrow(InvalidDataException::new);

    final var name = Content.Name
        .create(this.name)
        .getOrElseThrow(InvalidDataException::new);

    final var author = this.author.toEntity();
    final var parentTask = this.parentTask.toEntity();

    final var url = Content.Url
        .create(this.url)
        .getOrElseThrow(InvalidDataException::new);

    final var preview = Content.Url
        .create(this.preview)
        .getOrElseThrow(InvalidDataException::new);

    return new Content(id, type, name, dateCreated, author, format, url, preview);
  }
}
