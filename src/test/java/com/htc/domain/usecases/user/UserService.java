package com.htc.domain.usecases.user;

import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.UserRole;
import java.util.Random;

/**
 * Класс обслуживания сущности {@link User}.
 */
public abstract class UserService {

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
