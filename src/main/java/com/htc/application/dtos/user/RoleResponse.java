package com.htc.application.dtos.user;

import com.htc.domain.entities.user.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности роли.
 */
@EqualsAndHashCode
public class RoleResponse {
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
   * Создание основного представления роли.
   *
   * @param role сущность роли, подробнее  {@link Role}
   */
  public RoleResponse(Role role) {
    this.id = role.getId();
    this.name = role.getName();
  }
}