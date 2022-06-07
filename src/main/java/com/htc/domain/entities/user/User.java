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
   * Роль пользователя, см. {@link Role}.
   *
   * @return Роль пользователя.
   */
  private @Getter Role role;

  /**
   * Создаёт тестового пользователя с указанными идентификатором и ролью.
   *
   * @param id Идентификатор пользователя.
   * @param role Роль пользователя.
   * @return Пользователь.
   */
  public static User createTestUser(int id, Role role) {
    return new User(
            id,
            "user@example.com",
            "Passw0rd!",
            "Иванов Иван",
            new byte[] {},
            role
    );
  }

  /**
   * Создаёт тестового пользователя с указанным идентификатором.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  public static User createTestUser(int id) {
    var role = Role.ADMIN;
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
