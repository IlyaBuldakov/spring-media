package com.htc.application.dto.user;

import com.htc.domain.entities.User;
import lombok.Getter;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class UserResponse {
  /**
   * Идентификатор пользователя.
   *
   * @return id Идентификатор пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter int id;

  /**
   * Имя пользователя.
   *
   * @return id Имя пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String email;

  /**
   * Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String image;

  /**
   * Роль пользователя, см. {@link User.Role}.
   *
   * @return id Роль пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter User.Role role;

  /**
   * Создаёт экземпляр класса {@link UserResponse}.
   *
   * @param user Сущность пользователя.
   */
  public UserResponse(User user) {
    this.id = user.getId().getValue();
    this.name = user.getName().getValue();
    this.email = user.getEmail().getValue();
    this.image = user.getImage().getValue();
    this.role = user.getRole();
  }
}
