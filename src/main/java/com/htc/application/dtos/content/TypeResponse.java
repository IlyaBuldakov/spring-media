package com.htc.application.dtos.content;

import com.htc.domain.entities.content.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности типа.
 */
@EqualsAndHashCode
public class TypeResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Название.
   *
   * @return name название
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Создание основного представления типа.
   *
   * @param type сущность типа, подробнее  {@link Type}
   */
  public TypeResponse(Type type) {
    this.id = type.getId();
    this.name = type.getName();
  }
}
