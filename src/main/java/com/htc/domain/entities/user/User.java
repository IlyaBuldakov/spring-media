package com.htc.domain.entities.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import io.vavr.control.Either;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Пользователь.
 */
public class User {
  /**
   *  Идентификатор пользователя.
   *
   * @return id Идентификатор пользователя.
   */
  private @Getter int id;

  /**
   *  Имя пользователя.
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
   * @return password Пароль пользователя.
   */
  private @Getter String password;

  /**
   *  Изображение пользователя.
   *
   * @return avatar Изображение пользователя в кодировке Base64.
   */
  private @Getter String avatar;

  /**
   *  Роль пользователя, см. {@Link Role}
   *
   * @return role Роль пользователя.
   */
  private @Getter Role role;

  /**
   * Сообщение о некорректной электронной почте.
   */
  private static final String INVALID_EMAIL = "Некорректная электронная почта.";

  /**
   * Сообщение о некорректном пароле.
   */
  private static final String INVALID_PASSWORD = "Некорректный пароль.";

  /**
   * Создает пользователя и проверяет данные на корректность.
   *
   * @param id Идентификатор.
   * @param name Имя.
   * @param email Электронная почта.
   * @param password Пароль.
   * @param avatar Изображение.
   * @param role Роль.
   * @return Пользователь.
   */
  public static Either<Failure, User> create(
      int id, String name, String email, String password, String avatar, Role role) {
    if (!validateEmail(email)) {
      return Either.left(new InvalidValue(INVALID_EMAIL));
    }

    var user = new User();
    user.id = id;
    user.name = name;
    user.email = email;
    user.password = password;
    user.avatar = avatar;
    user.role = role;
    return Either.right(user);
  }

  /**
   * Проверяет на корректность электронную почту.
   *
   * @param email Электронная почта.
   * @return Результат проверки.
   */
  private static boolean validateEmail(String email) {
    return EmailValidator.getInstance().isValid(email);
  }

  /**
   * Проверяет на корректность пароль.
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
   * @param password Пароль.
   * @return Результат проверки.
   */
  private static boolean validatePassword(String password) {
    return password.matches("\\w{8,20}")
        && !password.toLowerCase().equals(password)
        && !password.toUpperCase().equals(password);
  }
}
