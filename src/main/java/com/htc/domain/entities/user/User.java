package com.htc.domain.entities.user;

import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Пользователь.
 */
@AllArgsConstructor
public class User {
  /**
   *  Идентификатор пользователя.
   *
   * @return id Идентификатор пользователя.
   */
  private @Getter int id;

  /**
   *  Электронная почта пользователя.
   *
   * @return id Электронная почта пользователя.
   */
  private @Getter String email;

  /**
   *  Пароль пользователя.
   *
   * @return id Пароль пользователя.
   */
  private @Getter String password;

  /**
   *  Имя пользователя.
   *
   * @return id Имя пользователя.
   */
  private @Getter String name;

  /**
   *  Изображение пользователя.
   *
   * @return id Изображение пользователя.
   */
  private @Getter byte[] avatar;

  /**
   *  Роль пользователя, см. {@Link UserRole}
   *
   * @return id Роль пользователя.
   */
  private @Getter UserRole role;

  /**
   * Создаёт тестового пользователя с указанными идентификатором и ролью.
   *
   * @param id Идентификатор пользователя.
   * @param role Роль пользователя.
   * @return Пользователь.
   */
  public static User createTestUser(int id, UserRole.RoleType role) {
    return new User(
        id,
        "user@example.com",
        "Passw0rd!",
        "Иванов Иван",
        new byte[] {},
        new UserRole(1, role)
    );
  }

  /**
   * Создаёт тестового пользователя с указанным идентификатором.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  public static User createTestUser(int id) {
    var role = UserRole.RoleType.ADMIN;
    return createTestUser(id, role);
  }

  /**
   * Создаёт тестового пользователя.
   *
   * @return Пользователь.
   */
  public static User createTestUser() {
    var id = new Random().nextInt();
    return createTestUser(id);
  }
}
