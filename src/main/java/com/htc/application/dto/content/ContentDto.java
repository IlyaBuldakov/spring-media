package com.htc.application.dto.content;

import com.htc.application.dto.user.UserResponse;
import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentFormat;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.user.User;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * Представление сущности медиаконтента.
 */
public class ContentDto {

  /**
   * Индентификатор медиаконтента.
   *
   * @return Индентификатор медиаконтента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;
  /**
   * Тип медиаконтента.
   *
   * @return Тип медиаконтента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter ContentTypeDto type;
  /**
   * Наименование медиаконтента.
   *
   * @return Наименование медиаконтента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;
  /**
   * Дата загрузки контента.
   *
   * @return Дата загрузки контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter LocalDateTime dateCreated;
  /**
   * Пользователь - автор контента.
   *
   * @return Пользователь - автор контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter UserShortResponse author;
  /**
   * Формат контента.
   *
   * @return Формат контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String format;
  /**
   * Адресс контента.
   *
   * @return Адресс контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String url;
  /**
   * Превью контента.
   *
   * @return Превью контента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String preview;

  /**
   * Создаёт экземпляр класса {@link ContentDto}.
   *
   * @param content Сущность медиаконтента.
   */
  public ContentDto(Content content) {
    this.id = content.getId();
    this.type = new ContentTypeDto(content.getType());
    this.name = content.getName();
    this.dateCreated = content.getDateCreated();
    this.author = new UserShortResponse(content.getAuthor());
    this.format = content.getFormat().name();
    this.url = content.getUrl();
    this.preview = content.getPreview();
  }
}