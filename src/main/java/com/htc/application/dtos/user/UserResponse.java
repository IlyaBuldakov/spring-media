package com.htc.application.dtos.user;

import com.htc.application.dtos.DataTransferObject;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Ответ. Основное представление сущности пользователя.
 */
public class UserResponse implements DataTransferObject<User> {
  /**
   * Идентификатор.
   *
   * @return id идентификатор
   */
  private final @Getter int id;

  /**
   * Полное имя.
   *
   * @return name полное имя
   */
  private final @Getter String name;

  /**
   * Электронная почта.
   *
   * @return email электронная почта
   */
  private final @Getter String email;

  /**
   * Аватар в base64.
   *
   * @return avatar аватар
   */
  private final @Getter String avatar;

  /**
   * Роль.
   *
   * @see Role
   * @return role роль
   */
  private final @Getter Role role;

  /**
   * Создание основного представления пользователя.
   *
   * @param user сущность пользователя, подробнее {@link User}
   */
  public UserResponse(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.avatar = user.getAvatar();
    this.role = user.getRole();
  }
}
