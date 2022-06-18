package com.htc.domain.entities.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import io.vavr.control.Either;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Пользователь.
 */
public class User {
  /**
   * <pre>Требования к имени.
   * Длина 3-20 символов (включительно).</pre>
   */
  private static final String NAME_REGEX = "^.*.{3,20}$";

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
   * @see User#validateId (int)
   *
   * @return id идентификатор
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter int id;

  /**
   * Полное имя пользователя.
   *
   * @see User#validateName (String)
   *
   * @return name полное имя пользователя
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String name;

  /**
   * Пароль.
   *
   * @see User#validatePassword (String)
   *
   * @return password пароль пользователя
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String password;

  /**
   * Электронная почта.
   *
   * @see User#validateEmail (String)
   *
   * @return email электронная почта пользователя
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String email;

  /**
   * Аватар в base64.
   *
   * @see User#validateImage (String)
   *
   * @return avatar аватар пользователя
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter String avatar;

  /**
   * Роль пользователя.
   *
   * @see Role
   *
   * @return role роль
   */
  @SuppressWarnings("JavadocDeclaration")
  private @Getter Role role;

  /**
   * Закрытый конструктор.
   * Функция: создание класса {@link User} только с помощью фабричного метода.
   */
  private User() {}

  /**
   * Создание пользователя с проверкой данных на корректность.
   *
   * @param id идентификатор
   * @param name полное имя
   * @param password пароль
   * @param email электронная почта
   * @param avatar изображение
   * @param role роль
   */
  public static Either<Failure, User> add(
          int id, String name, String password, String email, String avatar, Role role) {
    if (!validateId(id)) {
      return Either.left(InvalidValueParam.INVALID_ENTITY_ID);
    }
    if (!validateName(name)) {
      return Either.left(InvalidValueParam.INVALID_USER_NAME);
    }
    if (!validatePassword(password)) {
      return Either.left(InvalidValueParam.INVALID_USER_PASSWORD);
    }
    if (!validateEmail(email)) {
      return Either.left(InvalidValueParam.INVALID_USER_EMAIL);
    }
    if (!validateImage(avatar)) {
      return Either.left(InvalidValueParam.INVALID_USER_IMAGE);
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
   * Валидация идентификатора.
   *
   * @param id входящий параметр идентификатора
   * @return result флаг корректности идентификатора
   */
  private static boolean validateId(int id) {
    return id > 0;
  }

  /**
   * Валидация имени.
   *
   * @param name входящий параметр имени
   * @return result флаг корректности имени
   */
  private static boolean validateName(String name) {
    return name.matches(NAME_REGEX);
  }

  /**
   * Валидация пароля.
   *
   * @param password входящий параметр пароля
   * @return result флаг корректности пароля
   */
  private static boolean validatePassword(String password) {
    return password.matches(PASSWORD_REGEX);
  }

  /**
   * Валидация электронной почты.
   *
   * @param email входящий параметр электронной почты
   * @return result флаг корректности электронной почты
   */
  private static boolean validateEmail(String email) {
    return EmailValidator.getInstance().isValid(email);
  }

  /**
   * Валидация кодировки изображения.
   *
   * @param imageBase64 входящий параметр изображения в виде base64
   * @return result флаг корректности кодировки изображения
   */
  private static boolean validateImage(String imageBase64) {
    return Base64.isBase64(imageBase64) && (imageBase64.length() > 0);
  }
}
