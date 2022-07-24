package com.htc.infrastructure.models;

import com.htc.domain.entities.attributes.Id;
import com.htc.domain.entities.content.Content;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Модель медиаконтента для СУБД.
 */
@Entity
@Table(name = "Contents")
@AllArgsConstructor
public class ContentModel implements Content {

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
  private @Getter Type type;

  /**
   * Название медиаконтента.
   */
  @NotNull
  private String name;

  /**
   * Дата согдания медиаконтента.
   */
  @NotNull
  private @Getter LocalDateTime dateCreated;

  /**
   * Автор медиаконтента.
   */
  @NotNull
  @ManyToOne
  private @Getter UserModel author;

  /**
   * Формат медиаконтента.
   */
  @NotNull
  private @Getter Format format;

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

  @Override
  public Id getId() {
    return Id.create(contentId).get();
  }

  public Content.Name getName() {
    return Content.Name.create(this.name).get();
  }

  @Override
  public Url getUrl() {
    return Content.Url.create(this.url).get();
  }

  @Override
  public Url getPreview() {
    return Content.Url.create(this.preview).get();
  }

  protected ContentModel() {
  }

  /**
   * Создает модель медиаконтента.
   *
   * @param id Индентификатор медиаконтента.
   * @param type Тип медиаконтента.
   * @param name Наименование медиаконтента.
   * @param dateCreated Дата загрузки.
   * @param author Пользователь - автор медиаконтента.
   * @param format Формат медиаконтента.
   * @param url Адрес медиаконтента.
   * @param preview Адрес превью.
   */
  public ContentModel(Id id, Type type, Name name, LocalDateTime dateCreated, UserModel author,
                      Format format, Url url, Url preview) {
    this.contentId = id.getValue();
    this.type = type;
    this.name = name.getValue();
    this.dateCreated = dateCreated;
    this.author = author;
    this.format = format;
    this.url = url.getValue();
    this.preview = preview.getValue();
  }
}
