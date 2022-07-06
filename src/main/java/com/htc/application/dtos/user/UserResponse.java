package com.htc.application.dtos.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности пользователя.
 */
public class UserResponse {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter long id;

  /**
   * Полное имя.
   *
   * @return name полное имя
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String name;

  /**
   * Электронная почта.
   *
   * @return email электронная почта
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String email;

  /**
   * Аватар в base64.
   *
   * @return avatar аватар
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String avatar;

  /**
   * Роль.
   *
   * @see Role
   *
   * @return role роль
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Role role;

  /**
   * Создание основного представления пользователя.
   *
   * @param user сущность пользователя, подробнее {@link User}
   */
  public UserResponse(User user) {
    this.id = user.getId().getValue();
    this.name = user.getName().getValue();
    this.email = user.getEmail().getValue();
    this.avatar = user.getAvatar().getValue();
    this.role = user.getRole();
  }
}
