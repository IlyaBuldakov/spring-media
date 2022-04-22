package domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос данных пользователя.
 */
@AllArgsConstructor
public class UserRequestDto extends UserBasicDto {

  /**
   *Возвращает @return String email электронную почту пользователя.
   */
  private @Getter String email;

  /**
   * Возвращает @return byte[] avatar изображение пользователя.
   */
  private @Getter byte[] avatar;

  /**
   * Возвращает @return {@link RoleDto} роль пользователя.
   */
  private @Getter RoleDto role;

  /**
   * Возвращает @return String password пароль пользователя.
   */
  private @Getter String password;
}
