package com.htc.domain.entities.utility.parameters.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.EntityParameter;
import io.vavr.control.Either;
import java.util.Map;

/**
 *  Класс проверки значения имени пользователя.
 */
public class UserName extends EntityParameter<String> {
  /**
   * <pre>Требования к имени.
   * Длина 3-20 символов (включительно).</pre>
   */
  private static final String NAME_REGEX = "^.*.{3,20}$";

  private UserName(String value) {
    super(value);
  }

  /**
   * Создание класса проверки имени пользователя.
   *
   * @param value значение имени
   * @return result имя пользователя
   */
  public static Either<Failure, UserName> create(String value) {
    return (!value.matches(NAME_REGEX))
            ? Either.left(new InvalidValues(Map.of(InvalidValueParam.INVALID_ENTITY_NAME, "name")))
            : Either.right(new UserName(value));
  }
}
