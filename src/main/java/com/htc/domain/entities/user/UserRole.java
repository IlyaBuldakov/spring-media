package com.htc.domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Роль пользователя.
 */
@AllArgsConstructor
public class UserRole {
  /**
   * Виды ролей пользователя.
   */
  public enum RoleType {
    /**
     * Администратор.
     */
    ADMIN,
    /**
     * Менеджер.
     */
    MANAGER,
    /**
     * Контент-мейкер.
     */
    CONTENT_MAKER
  }

  /**
   * Идентификатор роли.
   *
   * @return id - идентификатор роли
   */
  private @Getter int id;

  /**
   * Название роли.
   *
   * @return name - название роли
   */
  private @Getter RoleType name;
}
