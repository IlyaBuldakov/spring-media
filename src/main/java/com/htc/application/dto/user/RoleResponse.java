package com.htc.application.dto.user;

import com.htc.domain.entities.User;

/**
 * Представление роли пользователя (ответ на запрос).
 * @param id Идентификатор роли.
 * @param name Название роли.
 */
public record RoleResponse(int id, String name) {

  /**
   * Создаёт экземпляр {@link RoleResponse}.
   *
   * @param role Роль пользователя.
   */
  public RoleResponse(User.Role role) {
    this(role.getId(), role.getName());
  }
}
