package com.htc.application.dto.content;

import com.htc.domain.entities.content.ContentType;
import lombok.Getter;

/**
 * Представление сущности типа медиаконтента.
 */
public class ContentTypeDto {
  /**
   * Идентификатор типа медиаконтента.
   *
   * @return id Идентификатор типа медиаконтента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Название типа медиаконтента.
   *
   * @return name Название типа медиаконтента.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Создаёт экземпляр класса {@link ContentTypeDto}.
   *
   * @param contentType Тип медиаконтента.
   */
  public ContentTypeDto(ContentType contentType) {
    this.id = contentType.getId();
    this.name = contentType.getName();
  }
}