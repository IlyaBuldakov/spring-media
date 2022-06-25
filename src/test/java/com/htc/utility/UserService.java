package com.htc.utility;

import com.github.javafaker.Faker;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import java.util.Locale;
import java.util.Random;

/**
 * Вспомогательные методы для работы с пользователями.
 */
public abstract class UserService {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final Role defaultRole = Role.ADMIN;

  /**
   * Создаёт тестового пользователя с указанными идентификатором и ролью.
   *
   * @param id Идентификатор пользователя.
   * @param role Роль пользователя.
   * @return Пользователь.
   */
  public static User createTestUser(int id, Role role) {
    return User.create(
        id,
        faker.name().fullName(),
        faker.internet().emailAddress(),
        faker.internet().password(5, 17) + "1aA",
        faker.lorem().characters(40),
        role
    ).get();
  }

  /**
   * Создаёт тестового пользователя с указанным идентификатором.
   *
   * @param id Идентификатор пользователя.
   * @return Пользователь.
   */
  public static User createTestUser(int id) {
    return createTestUser(id, defaultRole);
  }

  /**
   * Создаёт тестового пользователя.
   *
   * @return Пользователь.
   */
  public static User createTestUser() {
    var id = new Random().nextInt(255);
    return createTestUser(id);
  }
}
