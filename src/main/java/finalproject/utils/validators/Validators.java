package finalproject.utils.validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.Validate;

/**
 * Класс для валидации предоставленных клиентским сервисом данных.
 */
@NoArgsConstructor
public class Validators {

  static final String EMAIL_PATTERN =
          "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-]"
                  + "[\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";

  static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";

  /**
   * Валидация строки по шаблону.
   *
   * @param value  Строка
   * @param pattern Шаблон
   * @return boolean
   */
  public static boolean patternValidate(String value, String pattern) {
    try {
      Validate.matchesPattern(value, pattern);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Проверка строки на то, что она непустая.
   *
   * @param value Строка
   * @return boolean
   */
  public static boolean notNullString(String value) {
    try {
      Validate.isTrue(value.length() > 0);
    } catch (Exception e) {
      return false;
    }
    return true;
  }


@Getter
  private List<String> problems = new ArrayList<>();

  /**
   * Проверка e-mail по шаблону.
   *
   * @param email E-mail
   */
  public void validateEmail(String email) {
    if (!patternValidate(email, EMAIL_PATTERN)) {
      problems.add("email");
    }
  }

  /**
   * Проверка пароля по шаблону.
   *
   * @param password пароль. от 8 до 20 символов,
   *              обязательно в обоих регистрах, допускаются латинские буквы и цифры
   */
  public void validatePassword(String password) {
    if (!patternValidate(password, PASSWORD_PATTERN)) {
      problems.add("password");
    }

  }

  /**Проверка на ненулевую строку, если истина,
   * добавляется в список проблем некорректного заполнения полей.
   *
   * @param value Строка
   * @param field Поле
   */
  public void validateNonNullString(String value, String field) {
    if (!Validators.notNullString(value)) {
      problems.add(field);
    }
  }

  /**
   * Проверка на то, что передаваемый объект не является null.
   *
   * @param object Object
   * @param field поле
   */
  public boolean validateNotNull(Object object, String field) {
    try {
      Validate.notNull(object);
      return true;
    } catch (Exception e) {
      problems.add(field);
      return false;
    }
  }

  public boolean validateId(int id, String field) {
    try {
      Validate.isTrue(id > 0);
      return true;
    } catch (Exception e) {
      problems.add(field);
      return false;
    }
  }

  /**
   * Проверка на то, является ли передаваемое значение строкой Base64.
   *
   * @param image изображение в формате Base64
   */
  public void validateBase64(String image) {
    try {
      Validate.isTrue(Base64.isBase64(image) && Validators.notNullString(image));
    } catch (Exception e) {
      problems.add("image");
    }
  }

  /**
   * Проверка даты и времени на валидность.
   *
   * @param date дата
   * @return validated - проверенная строка
   */
  public LocalDateTime validateDateTime(String date) {
    LocalDateTime validated;
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      validated = LocalDateTime.parse(date, formatter);
    } catch (Exception e) {
      problems.add("dateExpired");
      return null;
    }
    if (validated.isBefore(LocalDateTime.now())) {
      problems.add("dateExpired");
      return null;
    }
    return validated;
  }

}
