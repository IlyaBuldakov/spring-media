package com.htc.domain.entities.utility.parameters.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.EntityParameter;
import io.vavr.control.Either;
import java.util.Map;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *  Класс проверки значения адреса почты пользователя.
 */
public class UserEmail extends EntityParameter<String> {
  private UserEmail(String value) {
    super(value);
  }

  /**
   * Создание класса проверки адреса почты пользователя.
   *
   * @param value значение адреса почты
   * @return result адрес почты пользователя
   */
  public static Either<Failure, UserEmail> create(String value) {
    return (!EmailValidator.getInstance().isValid(value))
            ? Either.left(new InvalidValues(Map.of(InvalidValueParam.INVALID_USER_EMAIL, "email")))
            : Either.right(new UserEmail(value));
  }
}
