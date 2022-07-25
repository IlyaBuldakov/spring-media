package com.htc.application.dto.user;

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
public record UserResponse(int id, String name, String email, String image, User.Role role) {
  /**
   * Создаёт экземпляр {@link UserResponse}.
   *
   * @param user Сущность пользователя.
   */
  public UserResponse(User user) {
    this(user.getId().getValue(),
            user.getName().getValue(),
            user.getEmail().getValue(),
            user.getImage().getValue(),
            user.getRole());
  }
}
