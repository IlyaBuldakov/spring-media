package com.htc.domain.entities.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import io.vavr.control.Either;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Пользователь.
 */
public class User {

  private static final String INVALID_EMAIL = "Некорректная электронная почта.";

  private static final String INVALID_PASSWORD = "Некорректный пароль.";

  /**
   * <pre>Требования к паролю.
   * Длина 8-20 символов (включительно);
   * Содержит символы только латинского алфавита;
   * Содержит символ верхнего регистра;
   * Содержит символ нижнего регистра;
   * Содержит числовой символ.</pre>
   */
  private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

  /**
   * Идентификатор.
   *
   * @return id - новый идентификатор
   */
  private @Getter int id;

  /**
   * Полное имя пользователя.
   *
   * @return name - полное имя пользователя
   */
  private @Getter String name;

  /**
   * Пароль.
   *
   * @return password - пароль пользователя
   */
  private @Getter String password;

  /**
   * Электронная почта.
   *
   * @return email - электронная почта пользователя
   */
  private @Getter String email;

  /**
   * Аватар в base64.
   *
   * @return avatar - аватар пользователя
   */
  private @Getter String avatar;

  /**
   * Роль пользователя.
   *
   * @return role - роль, подробнее {@link Role}
   */
  private @Getter Role role;

  /**
   * Создание пользователя с проверкой данных на корректность.
   *
   * @param  id - идентификатор
   * @param name - полное имя
   * @param password - пароль
   * @param email - электронная почта
   * @param avatar - аватар
   * @param role - роль
   */
  public static Either<Failure, User> add(
          int id, String name, String password, String email, String avatar, Role role) {
    if (!validateEmail(email)) {
      return Either.left(new InvalidValueParam(INVALID_EMAIL));
    }
    if (!validatePassword(password)) {
      return Either.left(new InvalidValueParam(INVALID_PASSWORD));
    }
    var user = new User();
    user.id = id;
    user.name = name;
    user.password = password;
    user.email = email;
    user.avatar = avatar;
    user.role = role;
    return Either.right(user);
  }

  /**
   * Валидация электронной почты.
   *
   * @param email - входящий параметр электронной почты
   * @return email - валидный параметр электронной почты
   */
  private static boolean validateEmail(String email) {
    return EmailValidator.getInstance().isValid(email);
  }

  /**
   * Валидация пароля.
   *
   * @param password - входящий параметр пароля
   * @return password - валидный параметр пароля
   */
  private static boolean validatePassword(String password) {
    return password.matches(PASSWORD_REGEX);
  }
}
