package domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User {
  /**
   * Идентификатор пользователя.
   *
   * @return Идентификатор пользователя.
   */
  private @Getter int id;
  /**
   * Электронная почта пользователя.
   *
   * @return Электронная почта пользователя.
   */
  private @Getter String email;
  /**
   * Пароль пользователя.
   *
   * @return Пароль пользователя.
   */
  private @Getter String password;
  /**
   * Имя пользователя.
   *
   * @return Имя пользователя.
   */
  private @Getter String name;
  /**
   * Изображение пользователя.
   *
   * @return Изображение пользователя.
   */
  private @Getter byte[] avatar;
  /**
   * Роль пользователя, см. {@link RoleDto}.
   *
   * @return Роль пользователя.
   */
  private @Getter RoleDto role;
}
