package com.htc.domain.entities.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import io.vavr.control.Either;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.validator.routines.EmailValidator;

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
    // Проверка идентификатора.
    if (id < 0) {
      return Either.left(InvalidValue.INVALID_ENTITY_ID);
    }

    // Проверка имени.
    if (name.length() == 0) {
      return Either.left(InvalidValue.INVALID_USER_NAME);
    }

    // Проверка почты.
    if (email.length() == 0 || !EmailValidator.getInstance().isValid(email)) {
      return Either.left(InvalidValue.INVALID_USER_EMAIL);
    }

    // Проверка пароля.
    if (!password.matches("\\w{8,20}")
            || !password.matches(".*\\d+.*")
            || password.toLowerCase().equals(password)
            || password.toUpperCase().equals(password)) {
      return Either.left(InvalidValue.INVALID_USER_PASSWORD);
    }

    // Проверка изображения.
    if (image.length() == 0 || !Base64.isBase64(image)) {
      return Either.left(InvalidValue.INVALID_USER_IMAGE);
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