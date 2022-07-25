package ru.kiryanovid.application.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.users.User;

/**
 * Представление пользователя.
 */
@AllArgsConstructor
public class UserDto {
  /**
   * Идентификатор пользователя.
   */
  @Getter private Integer id;

  /**
   * Имя пользователя.
   */
  @Getter private String name;

  /**
   * Электронная почта пользователя.
   */
  @Getter private String email;

  /**
   * Изображение пользователя.
   */
  @Getter private String avatar;

  /**
   * Роль пользователя.
   */
  @Getter private RoleDto role;

  /**
   * Получение представления пользователя.
   *
   * @param user представление пользователя
   *
   */

  public UserDto(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.avatar = user.getImage();
    this.role = new RoleDto(user);
  }
}
