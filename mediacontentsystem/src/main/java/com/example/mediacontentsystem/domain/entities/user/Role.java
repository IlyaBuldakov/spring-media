package com.example.mediacontentsystem.domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Роль пользователя.
 */
@AllArgsConstructor
public class Role {
  /**
   * Идентификатор роли.
   *
   * @return id Текущий идентификатор роли.
   */
  private @Getter int id;

  /**
   * Название роли, {@link RoleType}.
   *
   * @return name Текущее название роли.
   */
  private @Getter RoleType name;
}
