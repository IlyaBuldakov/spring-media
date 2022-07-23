package com.htc.application.dto.user;

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
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String name;

  /**
   * Электронная почта пользователя.
   *
   * @return Электронная почта пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String email;

  /**
   * Пароль пользователя.
   *
   * @return Пароль пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String password;

  /**
   * Изображение пользователя.
   *
   * @return Изображение пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String image;

  /**
   * Роль пользователя, см. {@link User.Role}.
   *
   * @return Роль пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter User.Role role;

  /**
   * Создаёт экземпляр класса {@link UserRequest}.
   *
   * @param user Сущность пользователя.
   */
  public UserRequest(User user) {
    this.name = user.getName().getValue();
    this.email = user.getEmail().getValue();
    this.password = user.getPassword().getValue();
    this.image = user.getImage().getValue();
    this.role = user.getRole();
  }

  public UserRequest() {
  }
}
