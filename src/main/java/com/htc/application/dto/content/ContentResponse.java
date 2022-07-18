package com.htc.application.dto.content;

import com.htc.domain.entities.Content;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности комментария (ответ на запрос).
 */
public class ContentResponse {
  /**
   * Идентификатор контента.
   *
   * @return id Идентификатор контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Тип контента.
   *
   * @return type Тип контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Content.Type type;

  /**
   * Название контента.
   *
   * @return name Название контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Дата создания контента.
   *
   * @return dateCreated Дата создания контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateCreated;

  /**
   * Автор контента.
   *
   * @return authorId Автор контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int authorId;

  /**
   * Формат контента.
   *
   * @return format Формат контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Content.Format format;

  /**
   * Путь к файлу контента.
   *
   * @return urlContent Путь к файлу контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String urlContent;

  /**
   * Путь к превью контента.
   *
   * @return urlPreview Путь к превью контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String urlPreview;

  /**
   * Создает экземпляр класса {@Link ContentResponse}.
   *
   * @param content Сущность контента.
   */
  public ContentResponse(Content content) {
    this.id = content.getId().getValue();
    this.type = content.getType();
    this.name = content.getName();
    this.dateCreated = content.getDateCreated();
    this.authorId = content.getAuthorId();
    this.format = content.getFormat();
    this.urlContent = content.getUrlContent();
    this.urlPreview = content.getUrlPreview();
  }
}
