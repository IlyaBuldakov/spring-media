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
   * Идентификатор.
   *
   * @return id - новый идентификатор
   */
  private @Getter int id;

  /**
   * Имя пользователя.
   *
   * @return name - имя пользователя
   */
  private @Getter String name;

  /**
   * Пароль.
   *
   * @return password - пароль пользователя
   */
  private @Getter String password;

  /**
   * Электронная почта.
   *
   * @return email - электронная почта пользователя
   */
  private @Getter String email;

  /**
   * Аватар.
   *
   * @return avatar - аватар пользователя
   */
  private @Getter String avatar;

  /**
   * Роль пользователя.
   *
   * @return role - роль, подробнее {@link UserRole}
   */
  private @Getter UserRole role;

  /**
   * Создание экземпляра тестового пользователя c указанным идентификатором и ролью.
   *
   * @param id - идентификатор
   * @param role - роль
   * @return user - тестовый пользователь
   */
  public static User createTestUser(int id, UserRole.RoleType role) {
    return new User(
            id,
            "mail@mail.ru",
            "hardPassword",
            "First Second Name",
            "1234567890==",
            new UserRole(1, role)
    );
  }

  /**
   * Создание экземпляра тестового пользователя c указанным идентификатором.
   *
   * @param id - идентификатор
   * @return user - тестовый пользователь
   */
  public static User createTestUser(int id) {
    return createTestUser(id, UserRole.RoleType.ADMIN);
  }

  /**
   * Создание экземпляра тестового пользователя.
   *
   * @return user - тестовый пользователь
   */
  public static User createTestUser() {
    return createTestUser(new Random().nextInt());
  }
}
