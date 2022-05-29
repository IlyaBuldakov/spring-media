package com.htc.domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Пользователь.
 */
@AllArgsConstructor
public class User {
  /**
   *  Идентификатор пользователя.
   *
   * @return id Идентификатор пользователя.
   */
  private @Getter int id;

  /**
   *  Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  private @Getter String email;

  /**
   *  Пароль пользователя.
   *
   * @return id Пароль пользователя.
   */
  private @Getter String password;

  /**
   *  Имя пользователя.
   *
   * @return id Имя пользователя.
   */
  private @Getter String name;

  /**
   *  Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  private @Getter byte[] avatar;

  /**
   *  Роль пользователя, см. {@Link UserRole}
   *
   * @return id Роль пользователя.
   */
  private @Getter UserRole role;
}
