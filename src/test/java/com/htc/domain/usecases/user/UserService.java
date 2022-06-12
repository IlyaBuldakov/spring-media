package com.htc.domain.usecases.user;

import com.github.javafaker.Faker;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import java.util.Locale;
import java.util.Random;

/**
 * Вспомогательные методы для работы с пользователями.
 */
public class UserService {
  private static final Faker faker = Faker.instance(new Locale("ru"));
  private static final Role defaultRole = Role.ADMIN;

  /**
   * Создаёт тестового пользователя с указанными идентификатором и ролью.
   *
   * @param id   Идентификатор пользователя.
   * @param role Роль пользователя.
   * @return Пользователь.
   */
  public static User createTestUser(int id, Role role) {
    return User.create(
                    id,
                    faker.internet().emailAddress(),
                    faker.internet().password(8, 12),
                    faker.name().fullName(),
                    faker.lorem().characters(40),
                    role)
            .get();
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
    var id = new Random().nextInt();
    return createTestUser(id);
  }
}
