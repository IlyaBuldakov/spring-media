package com.htc.domain.entities.utility.parameters;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import io.vavr.control.Either;
import java.util.Map;

/**
 *  Класс проверки значения пароля пользователя.
 */
public class UserPassword extends EntityParameter<String> {
  /**
   * <pre>Требования к паролю.
   * Длина 8-20 символов (включительно);
   * Содержит символы только латинского алфавита;
   * Содержит символ верхнего регистра;
   * Содержит символ нижнего регистра;
   * Содержит числовой символ.</pre>
   */
  private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

  private UserPassword(String value) {
    super(value);
  }

  /**
   * Создание класса проверки пароля пользователя.
   *
   * @param value значение пароля
   * @return result пароль пользователя
   */
  public static Either<Failure, UserPassword> create(String value) {
    return (!value.matches(PASSWORD_REGEX))
            ? Either.left(new InvalidValues(
                    Map.of(InvalidValueParam.INVALID_USER_PASSWORD, "password")))
            : Either.right(new UserPassword(value));
  }
}
