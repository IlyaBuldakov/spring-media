package com.htc.domain.entities.user;

import lombok.Getter;

/**
 * Класс, описывающий роль пользователя.
 */
public enum Role {

  ADMIN(1, "Администратор"),

  MANAGER(2, "Менеджер"),

  CONTENT_MAKER(3, "Контент-мейкер");

  Role(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public static Role lookup(String roleName) {
    try {
      return Role.valueOf(roleName.toUpperCase());
    } catch (IllegalArgumentException exception) {
      return null;
    }
  }

  /**
   * Идентификатор роли.
   */
  private final @Getter int id;

  /**
   * Имя роли.
   */
  private final @Getter String name;
}
