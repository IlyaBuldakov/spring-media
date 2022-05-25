package com.htc.domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Пользователь.
 */
@AllArgsConstructor
public class User {
  /**
   * Идентификатор.
   *
   * @return id - новый идентификатор
   */
  private @Getter int id;

  /**
   * Имя пользователя.
   *
   * @return name - имя пользователя
   */
  private @Getter String name;

  /**
   * Пароль.
   *
   * @return password - пароль пользователя
   */
  private @Getter String password;

  /**
   * Электронная почта.
   *
   * @return email - электронная почта пользователя
   */
  private @Getter String email;

  /**
   * Аватар в base64.
   *
   * @return avatar - аватар пользователя
   */
  private @Getter String avatar;

  /**
   * Роль пользователя.
   *
   * @return role - роль, подробнее {@link UserRole}
   */
  private @Getter UserRole role;
}
