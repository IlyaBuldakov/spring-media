package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
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
   * Роль пользователя, см. {@link Role}.
   *
   * @return id Роль пользователя.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Role role;

  /**
   * Создаёт экземпляр класса {@link UserResponse}.
   *
   * @param user Сущность пользователя.
   */
  public UserResponse(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.image = user.getImage();
    this.role = user.getRole();
  }



}
