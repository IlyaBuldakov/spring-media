package finalproject.application.dto.user;

import finalproject.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Пользователь.
 */
@AllArgsConstructor
public class UserDto extends UserBasicDto {

  /**
   * Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  private @Getter String email;

  /**
   * Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  private @Getter String avatar;

  /**
   * Роль пользователя, см. {@link RoleDto}.
   *
   * @return id Роль пользователя.
   */
  private @Getter RoleDto role;

  public UserDto(User user) {
    super(user.getId(), user.getName());
    this.email = user.getEmail();
    this.avatar = user.getAvatar();
    this.role = new RoleDto(user.getRole().getId(), user.getRole().getName());
  }
}
