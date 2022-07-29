package com.htc.domain.entities.utility.parameters;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import io.vavr.control.Either;
import java.util.Map;

/**
 *  Класс проверки значения поля имени сущности.
 */
public class EntityName extends EntityParameter<String> {
  /**
   * <pre>Требования к имени.
   * Длина 3-20 символов (включительно).</pre>
   */
  private static final String NAME_REGEX = "^.*.{3,20}$";

  private EntityName(String value) {
    super(value);
  }

  /**
   * Создание класса проверки поля имени сущности.
   *
   * @param value значение поля
   * @return result поле имени сущности
   */
  public static Either<Failure, EntityName> create(String value) {
    return (!value.matches(NAME_REGEX))
            ? Either.left(new InvalidValues(Map.of(InvalidValueParam.INVALID_ENTITY_NAME, "name")))
            : Either.right(new EntityName(value));
  }
}
