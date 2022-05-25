package com.htc.domain.usecases.user;

import com.github.javafaker.Faker;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.user.UserRole;
import java.util.Locale;
import java.util.Random;

/**
 * Класс обслуживания сущности {@link User}.
 */
public abstract class UserService {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final UserRole.RoleType defaultRole = UserRole.RoleType.ADMIN;

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
            faker.name().fullName(),
            faker.internet().password(8, 12),
            faker.internet().emailAddress(),
            faker.lorem().characters(40) + "==",
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
    return createTestUser(id, defaultRole);
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
