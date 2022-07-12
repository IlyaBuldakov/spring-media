package com.htc.utility;

import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.failures.InvalidValues;
import org.springframework.stereotype.Component;

/**
 * Вспомогательный класс для проверки данных пользователя.
 */
@Component
public class UserValidator {
  private static final String UPPERCASE_LETTER = ".*[A-Z].*";
  private static final String LOWERCASE_LETTER = ".*[a-z].*";
  private static final String DIGITAL_CHARACTER = ".*[\\d].*";
  private static final String AVAILABLE_PASSWORD_LENGTH = "\\w{8,20}";

  /**
   * Проверяет переданые поля на корректность.
   *
   * @param id Индентификатор пользователя.
   * @param name Имя пользователя.
   * @param email Электронная почта пользователя.
   * @param password Пароль пользоваетля.
   * @param image Изображение пользоваетля.
   * @return InvalidValues - ошибка содержащая список некорректно введеных полей.
   */
  public static InvalidValues validateUserFields(
          int id,
          String name,
          String email,
          String password,
          String image) {
    InvalidValues invalidValues = new InvalidValues();

    invalidValues.addInvalidValue(validUserId(id));
    invalidValues.addInvalidValue(validUserName(name));
    invalidValues.addInvalidValue(validUserPassword(email));
    invalidValues.addInvalidValue(validUserEmail(password));
    invalidValues.addInvalidValue(validUserImage(image));

    if (invalidValues.getInvalidValues().isEmpty()) {
      return null;
    }

    return invalidValues;
  }

  private static InvalidValue validUserId(int id) {
    return null;
  }

  private static InvalidValue validUserName(String name) {
    return null;
  }

  private static InvalidValue validUserPassword(String password) {
    if (password.matches(UPPERCASE_LETTER)
            && password.matches(LOWERCASE_LETTER)
            && password.matches(DIGITAL_CHARACTER)
            && password.matches(AVAILABLE_PASSWORD_LENGTH)) {
      return null;
    }
    System.out.println("INVALID_USER_PASSWORD");
    return InvalidValue.INVALID_USER_PASSWORD;

  }

  private static InvalidValue validUserEmail(String email) {
    return null;
  }

  private static InvalidValue validUserImage(String image) {
    return null;
  }
}