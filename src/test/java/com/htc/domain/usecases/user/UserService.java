package com.htc.domain.usecases.user;

import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.UserRole;
import java.util.Random;

/**
 * Вспомогательные методы для работы с пользователями.
 */
public abstract class UserService {
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
