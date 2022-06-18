package com.htc.application.dtos.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Запрос. Основное представление сущности пользователя.
 */
public class UserRequest {
  /**
   * Полное имя.
   *
   * @return name полное имя
   */
  private final @Getter String name;

  /**
   * Электронная почта.
   *
   * @return email электронная почта
   */
  private final @Getter String email;

  /**
   * Пароль.
   *
   * @return password пароль
   */
  private final @Getter String password;

  /**
   * Аватар в base64.
   *
   * @return avatar аватар
   */
  private final @Getter String avatar;

  /**
   * Роль.
   *
   * @see Role
   * @return role роль
   */
  private final @Getter Role role;

  /**
   * Создание основного представления пользователя.
   *
   * @param user сущность пользователя, подробнее {@link User}
   */
  public UserRequest(User user) {
    this.name = user.getName();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.avatar = user.getAvatar();
    this.role = user.getRole();
  }
}
