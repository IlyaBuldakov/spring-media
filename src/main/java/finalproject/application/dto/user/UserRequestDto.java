package finalproject.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос данных пользователя.
 */
@AllArgsConstructor
public class UserRequestDto {

  /**
   *Возвращает @return String name электронную почту пользователя.
   */
  private @Getter String name;
  /**
   *Возвращает @return String email электронную почту пользователя.
   */
  private @Getter String email;

  /**
   * Возвращает @return byte[] avatar изображение пользователя.
   */
  private @Getter String avatar;

  /**
   * Возвращает @return {@link RoleDto} роль пользователя.
   */
  private @Getter String role;

  /**
   * Возвращает @return String password пароль пользователя.
   */
  private @Getter String password;
}
