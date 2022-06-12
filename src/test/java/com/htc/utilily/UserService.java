package com.htc.utilily;

import com.github.javafaker.Faker;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import java.util.Locale;
import java.util.Random;

/**
 * Класс обслуживания сущности {@link User}.
 */
public final class UserService {
  private static final Faker faker = Faker.instance(new Locale("ru"));

  private UserService() {}

  /**
   * Создание экземпляра тестового пользователя c указанным идентификатором и ролью.
   *
   * @param id идентификатор
   * @param role роль
   * @return user тестовый пользователь
   */
  public static User createTestUser(int id, Role role) {
    return User.add(
            id,
            faker.name().fullName(),
            "gTeggstiag4",
            faker.internet().emailAddress(),
            faker.lorem().characters(40) + "==",
            role
    ).get();
  }

  /**
   * Создание экземпляра тестового пользователя c указанным идентификатором.
   *
   * @param id идентификатор
   * @return user тестовый пользователь
   */
  public static User createTestUser(int id) {
    var role = createTestRole();
    return createTestUser(id, role);
  }

  /**
   * Создание экземпляра тестового пользователя.
   *
   * @return user тестовый пользователь
   */
  public static User createTestUser() {
    return createTestUser(new Random().nextInt(1, 32));
  }

  /**
   * Создание экземпляра случайной тестовой роли.
   *
   * @return role случайная тестовая роль
   */
  public static Role createTestRole() {
    var roles = Role.values();
    var roleIndex = new Random().nextInt(roles.length);
    return roles[roleIndex];
  }
}
