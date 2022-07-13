package com.htc.application.dto.user;

import com.htc.domain.entities.User;

/**
 * Представление сущности пользователя (запрос).
 */
public class UserRequest {
  /**
   * Имя пользователя.
   *
   * @return id Имя пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String name;

  /**
   * Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String email;

  /**
   * Пароль пользователя.
   *
   * @return id Пароль пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String password;

  /**
   * Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  public String image;

  /**
   * Роль пользователя, см. {@link User.Role}.
   *
   * @return id Роль пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  public User.Role role;

  private UserRequest() {}

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
}
