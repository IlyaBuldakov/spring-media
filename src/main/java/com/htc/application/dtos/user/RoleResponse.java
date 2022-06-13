package com.htc.application.dtos.user;

import com.htc.application.dtos.DataTransferObject;
import com.htc.domain.entities.user.Role;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности роли.
 */
public class RoleResponse implements DataTransferObject<Role> {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  private final @Getter int id;

  /**
   * Название.
   *
   * @return name название
   */
  private final @Getter String name;

  /**
   * Создание основного представления пользователя.
   *
   * @param role сущность роли, подробнее  {@link Role}
   */
  public RoleResponse(Role role) {
    this.id = role.getId();
    this.name = role.getName();
  }
}
