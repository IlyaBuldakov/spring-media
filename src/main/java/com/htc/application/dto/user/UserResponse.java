package com.htc.application.dto.user;

import com.htc.domain.entities.Entity;
import com.htc.domain.entities.User;

/**
 * Представление сущности пользователя (ответ на запрос).
 *
 * @param id Идентификатор пользователя.
 * @param name Имя пользователя.
 * @param email Электронная почта пользователя.
 * @param image Изображение пользователя.
 * @param role Роль пользователя, см. {@link User.Role}.
 */
public record UserResponse(int id, String name, String email, String image,  RoleResponse role)
        implements Entity.View<UserResponse, User> {
  @Override
  public UserResponse fromEntity(User user) {
    return new UserResponse(user);
  }
  /**
   * Создаёт экземпляр {@link UserResponse}.
   *
   * @param user Сущность пользователя.
   */
  public UserResponse(User user) {
    this(user.id().getValue(),
            user.name().getValue(),
            user.email().getValue(),
            user.image().getValue(),
            new RoleResponse(user.role()));
  }

  /**
   * Представление роли пользователя (ответ на запрос).
   *
   * @param id Идентификатор роли.
   * @param name Название роли.
   */
  record RoleResponse(int id, User.Role name) {
    public RoleResponse(User.Role role) {
      this(role.getId(), role);
    }
  }
}
