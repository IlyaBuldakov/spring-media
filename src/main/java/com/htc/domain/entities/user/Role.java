package com.htc.domain.entities.user;

import lombok.Getter;

/**
 * Роль пользователя.
 */

public enum Role {
  /**
   * Администратор.
   */
  ADMIN(1, "Администратор"),
  /**
   * Менеджер.
   */
  MANAGER(2, "Менеджер"),
  /**
   * Контент-мейкер.
   */
  CONTENT_MAKER(3, "Контент-мейкер");

  /**
   * Идентификатор роли.
   *
   * @return id - идентификатор роли
   */
  private final @Getter int id;

  /**
   * Название роли.
   *
   * @return name - название роли
   */
  private final @Getter String name;

  Role(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
