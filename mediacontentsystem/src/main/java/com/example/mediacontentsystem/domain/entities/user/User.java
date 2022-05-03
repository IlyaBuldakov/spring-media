package com.example.mediacontentsystem.domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Пользователь.
 */
@AllArgsConstructor
public class User {
  /**
   * Идентификатор пользователя.
   *
   * @return id текущий идентификатор пользователя.
   */
  private @Getter int id;

  /**
   * Имя пользователя.
   *
   * @param name Новое имя пользователя.
   * @return name Текущее имя пользователя.
   */
  private @Getter @Setter String name;

  /**
   * Электронная почта пользователя.
   *
   * @param email Новая электронная почта пользователя.
   * @return email Текущая электронная почта пользователя.
   */
  private @Getter @Setter String email;

  /**
   * Пароль пользователя.
   *
   * @param password Новый пароль пользователя.
   * @return password Текущий пароль пользователя.
   */
  private @Getter @Setter String password;

  /**
   * Изображение пользователя.
   *
   * @param avatar Новое изображение пользователя.
   * @return avatar Текущее изображение пользователя.
   */
  private @Getter @Setter byte[] avatar;

  /**
   * Роль пользователя, {@link Role}.
   *
   * @param role Новая роль пользователя.
   * @return role Текущая роль пользователя.
   */
  private @Getter @Setter Role role;
}
