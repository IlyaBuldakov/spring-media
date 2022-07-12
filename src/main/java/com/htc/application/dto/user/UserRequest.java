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
   * @return Имя пользователя.
   */
  private @Getter String name;

  /**
   * Электронная почта пользователя.
   *
   * @return Электронная почта пользователя.
   */
  private @Getter String email;

  /**
   * Пароль пользователя.
   *
   * @return Пароль пользователя.
   */
  private @Getter String password;

  /**
   * Изображение пользователя.
   *
   * @return Изображение пользователя.
   */
  private  @Getter String image;

  /**
   * Роль пользователя, см. {@link Role}.
   *
   * @return Роль пользователя.
   */
  private @Getter Role role;

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

  public UserRequest() {
  }
}
