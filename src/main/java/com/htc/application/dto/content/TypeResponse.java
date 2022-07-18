package com.htc.application.dto.content;

import com.htc.domain.entities.Content;
import lombok.Getter;

/**
 * Представление типа контента (ответ на запрос).
 */
public class TypeResponse {
  /**
   * Идентификатор типа.
   *
   * @return id Идентификатор типа.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Название типа.
   *
   * @return name Название типа.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Создает экземпляр класса {@Link TypeResponse}.
   *
   * @param type Тип контента.
   */
  public TypeResponse(Content.Type type) {
    this.id = type.getId();
    this.name = type.getName();
  }
}
