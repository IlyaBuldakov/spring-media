package com.htc.application.dto.user;

import com.htc.domain.entities.Entity;
import com.htc.domain.entities.User;

/**
 * Представление сущности пользователя (запрос).
 *
 * @param name Имя пользователя.
 * @param email Электронная почта пользователя.
 * @param password Пароль пользователя.
 * @param image Изображение пользователя.
 * @param role Роль пользователя, см. {@link User.Role}.
 */
public record UserRequest(
        String name,
        String email,
        String password,
        String image,
        User.Role role)
        implements Entity.View<UserRequest, User> {

  @Override
  public UserRequest fromEntity(User user) {
    return new UserRequest(user);
  }

  /**
   * Создаёт экземпляр класса {@link UserRequest}.
   *
   * @param user Сущность пользователя.
   */
  public UserRequest(User user) {
    this(user.name().getValue(),
            user.email().getValue(),
            user.password().getValue(),
            user.image().getValue(),
            user.role());
  }
}
