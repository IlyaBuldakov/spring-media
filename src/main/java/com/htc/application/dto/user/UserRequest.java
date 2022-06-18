package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Представление сущности пользователя (запрос).
 */
public class UserRequest {
  /**
   * Имя пользователя.
   *
   * @return id Имя пользователя.
   */
  private final @Getter String name;

  /**
   * Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  private final @Getter String email;

  /**
   * Пароль пользователя.
   *
   * @return id Пароль пользователя.
   */
  private final @Getter String password;

  /**
   * Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  private final @Getter String image;

  /**
   * Роль пользователя, см. {@link Role}.
   *
   * @return id Роль пользователя.
   */
  private final @Getter Role role;

  /**
   * Создаёт экземпляр класса {@link UserRequest}.
   *
   * @param user Сущность пользователя.
   */
  public UserRequest(User user) {
    this.name = user.getName();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.image = user.getImage();
    this.role = user.getRole();
  }
}
