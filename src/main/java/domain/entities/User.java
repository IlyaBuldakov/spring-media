package domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Пользователь.
 */
@AllArgsConstructor
public class User {
  /**
   * Идентификатор.
   *
   * @return id - новый идентификатор
   */
  @Getter private int id;

  /**
   * Имя пользователя.
   *
   * @return name - имя пользователя
   */
  @Getter private String name;

  /**
   * Пароль.
   *
   * @return password - пароль пользователя
   */
  @Getter private String password;

  /**
   * Электронная почта.
   *
   * @return email - электронная почта пользователя
   */
  @Getter private String email;

  /**
   * Аватар.
   *
   * @return avatar - аватар пользователя
   */
  @Getter private String avatar;

  /**
   * Роль, подробнее {@link UserRole}.
   *
   * @return role - роль пользователя
   */
  @Getter private UserRole role;
}
