package com.htc.application.dtos.user;

import com.htc.domain.entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос. Основное представление сущности пользователя.
 */
@AllArgsConstructor
public class UserRequest {
  /**
   * Полное имя.
   *
   * @return name полное имя
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String name;

  /**
   * Электронная почта.
   *
   * @return email электронная почта
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String email;

  /**
   * Пароль.
   *
   * @return password пароль
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String password;

  /**
   * Аватар в base64.
   *
   * @return avatar аватар
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String avatar;

  /**
   * Роль.
   *
   * @see Role
   *
   * @return role роль
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Role role;

  private UserRequest() {}
}
