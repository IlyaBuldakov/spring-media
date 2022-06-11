package com.htc.utilily;

import com.github.javafaker.Faker;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import java.util.Locale;
import java.util.Random;

/**
 * Класс обслуживания сущности {@link User}.
 */
public abstract class UserService {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final Role defaultRole = Role.ADMIN;

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
    return createTestUser(id, defaultRole);
  }

  /**
   * Создание экземпляра тестового пользователя.
   *
   * @return user тестовый пользователь
   */
  public static User createTestUser() {
    return createTestUser(new Random().nextInt(1, 32));
  }
}
