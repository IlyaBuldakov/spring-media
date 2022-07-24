package com.htc.application.dto.content;

import com.htc.domain.entities.content.Content;

/**
 * Представление сущности типа медиаконтента.
 *
 * @param id Идентификатор типа медиаконтента.
 * @param name Название типа медиаконтента.
 */
public record ContentTypeDto(int id, String name) {
    /**
   * Создаёт экземпляр класса {@link ContentTypeDto}.
   *
   * @param contentType Тип медиаконтента.
   */
  public ContentTypeDto(Content.Type contentType) {
    this(contentType.getId(), contentType.getName());
  }
}