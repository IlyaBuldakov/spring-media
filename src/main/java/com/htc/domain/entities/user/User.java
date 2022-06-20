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
   *  Идентификатор пользователя. Идентификатор – не отрицательное число.
   *
   * @return id Идентификатор пользователя.
   */
  private @Getter int id;

  /**
   *  Имя пользователя. Имя не пустая строка.
   *
   * @return name Имя пользователя.
   */
  private @Getter String name;

  /**
   *  Электронная почта пользователя.
   *
   * @return email Электронная почта пользователя.
   */
  private @Getter String email;

  /**
   *  Пароль пользователя.
   *
   * <p>
   * Корректный пароль:
   * 1. длина пароля от 8 до 20 символов (включительно)
   * 2. содержащий символы латинского алфавита обоих регистров,
   * 3. пароль содержит цифры,
   * 4. пароль может содеражть знак подчеркивания.
   * </p>
   *
   * <p>
   * Пример корректного пароля: {@code abcdEFG1234}
   * Пример некорректного пароля: {@code abc}
   *</p>
   *
   * @return password Пароль пользователя.
   */
  private @Getter String password;

  /**
   *  Изображение пользователя.
   *
   * @return image Изображение пользователя представлено в кодировке Base64.
   */
  private @Getter String image;

  /**
   *  Роль пользователя, см. {@Link Role}
   *
   * @return role Роль пользователя.
   */
  private @Getter Role role;

  private User() {}

  private static final String INVALID_ID = "Некорректный идентификатор.";
  private static final String INVALID_NAME = "Некорректное имя.";

  private static final String INVALID_EMAIL = "Некорректная электронная почта.";

  private static final String INVALID_PASSWORD = "Некорректный пароль.";
  private static final String INVALID_IMAGE = "Некорректное изображение.";

  /**
   * Создает пользователя и проверяет данные на корректность.
   *
   * @param id Идентификатор.
   * @param name Имя.
   * @param email Электронная почта.
   * @param password Пароль.
   * @param image Изображение.
   * @param role Роль.
   * @return Пользователь.
   */
  public static Either<Failure, User> create(
      int id, String name, String email, String password, String image, Role role) {

    // Проверка идентификатора.
    if (id < 0) {
      return Either.left(new InvalidValue(INVALID_ID));
    }

    // Проверка имени.
    if (name.length() == 0) {
      return Either.left(new InvalidValue(INVALID_NAME));
    }

    // Проверка почты.
    if (email.length() == 0 || !EmailValidator.getInstance().isValid(email)) {
      return Either.left(new InvalidValue(INVALID_EMAIL));
    }

    // Проверка пароля.
    if (!password.matches("\\w{8,20}")
        || !password.matches(".*\\d+.*")
        || password.toLowerCase().equals(password)
        || password.toUpperCase().equals(password)) {
      return Either.left(new InvalidValue(INVALID_PASSWORD));
    }

    // Проверка изображения.
    if (image.length() == 0 || !Base64.isBase64(image)) {
      return Either.left(new InvalidValue(INVALID_IMAGE));
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
