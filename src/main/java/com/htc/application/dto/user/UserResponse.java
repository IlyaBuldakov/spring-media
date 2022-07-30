package com.htc.application.dto.user;

import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Представление сущности пользователя (ответ).
 */
@AllArgsConstructor
public class UserResponse {

  /**
   * Конструктор из соответствующей сущности.
   *
   * @param user Сущность {@link User пользователя}.
   */
  public UserResponse(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.avatar = user.getAvatar();
    this.role = new RoleResponse(user.getRole());
  }

  /**
   * Идентификатор пользователя.
   */
  private final @Getter int id;

  /**
   * Имя пользователя.
   */
  private final @Getter String name;

  /**
   * E-mail пользователя.
   */
  private final @Getter String email;

  /**
   * Аватар пользователя.
   */
  private final @Getter String avatar;

  /**
   * Роль пользователя.
   */
  private final @Getter RoleResponse role;
}
