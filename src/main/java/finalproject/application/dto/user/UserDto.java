package finalproject.application.dto.user;

import finalproject.domain.entities.user.User;
import lombok.Getter;

/**
 * Пользователь.
 */

public class UserDto extends UserBasicDto {

  /**
   * Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  private final @Getter String email;

  /**
   * Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  private final @Getter String avatar;

  /**
   * Роль пользователя, см. {@link RoleDto}.
   *
   * @return id Роль пользователя.
   */
  private final @Getter RoleDto role;

  /**
   * Конструктор представления пользователя.
   *
   * @param user - пользователь.
   */
  public UserDto(User user) {
    super(user.getId(), user.getName());
    this.email = user.getEmail();
    this.avatar = user.getAvatar();
    this.role = new RoleDto(user.getRole().getId(), user.getRole().getName());
  }
}
