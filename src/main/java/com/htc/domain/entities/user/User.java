package com.htc.domain.entities.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.utility.UserValidator;
import io.vavr.control.Either;
import lombok.Getter;

/**
 * Пользователь.
 */
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
  private @Getter String image;
  /**
   * Роль пользователя, см. {@link Role}.
   *
   * @return Роль пользователя.
   */
  private @Getter Role role;

  private User() {
  }

  /**
   * Создаёт пользователя и проверяет данные на корректность.
   *
   * @param id       Идентификатор.
   * @param name     Имя.
   * @param email    Электронная почта.
   * @param password Пароль.
   * @param image    Изображение.
   * @param role     Роль.
   * @return Пользователь.
   */
  public static Either<Failure, User> create(
          int id, String name, String email, String password, String image, Role role) {

    InvalidValues invalidValues = UserValidator.validateUserFields(id,
            name,
            email,
            password,
            image);
    if (invalidValues != null) {
      return Either.left(invalidValues);
    }

    var user = new User();
    user.id = id;
    user.name = name;
    user.email = email;
    user.password = password;
    user.image = image;
    user.role = role;
    return Either.right(user);

  }
}