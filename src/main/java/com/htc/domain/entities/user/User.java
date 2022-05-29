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
   *  Имя пользователя.
   *
   * @return id Имя пользователя.
   */
  private @Getter String name;

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
   *  Изображение пользователя.
   *
   * @return id Изображение пользователя в кодировке Base64.
   */
  private @Getter String avatar;

  /**
   *  Роль пользователя, см. {@Link UserRole}
   *
   * @return id Роль пользователя.
   */
  private @Getter UserRole role;
}
