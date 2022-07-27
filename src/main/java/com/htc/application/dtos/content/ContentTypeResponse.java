package com.htc.application.dtos.content;

import com.htc.domain.entities.content.Content;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности типа контента.
 */
@EqualsAndHashCode
public class ContentTypeResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Long id;

  /**
   * Название.
   *
   * @return name название
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Создание основного представления типа контента.
   *
   * @param content сущность роли, подробнее  {@link Content}
   */
  public ContentTypeResponse(Content content) {
    this.id = content.getId().getValue();
    this.name = content.getName().getValue();
  }
}
