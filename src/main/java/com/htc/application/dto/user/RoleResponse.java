package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import lombok.Getter;

/**
 * Представление роли пользователя (ответ на запрос).
 */
public class RoleResponse {
  /**
   * Идентификатор роли.
   *
   * @return id Идентификатор роли.
   */
  private final @Getter int id;

  /**
   * Название роли.
   *
   * @return name Название роли.
   */
  private final @Getter String name;

  /**
   * Создаёт экземпляр класса {@link RoleResponse}.
   *
   * @param role Роль пользователя.
   */
  public RoleResponse(Role role) {
    this.id = role.getId();
    this.name = role.getName();
  }
}
