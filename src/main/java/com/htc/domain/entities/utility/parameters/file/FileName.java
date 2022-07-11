package com.htc.domain.entities.utility.parameters.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.file.Format;
import com.htc.domain.entities.utility.parameters.EntityParameter;
import io.vavr.control.Either;
import java.util.Map;

/**
 *  Класс проверки значения имени файла.
 */
public class FileName extends EntityParameter<String> {
  /**
   * <pre>Требования к имени файла.
   * Длина 1-63 символов (включительно).
   * Содержит любой цифробуквенный символ латиницей.
   * Содержит символ '_'.
   * Формат файла должен быть из {@link Format списка}.
   * </pre>
   */
  public static final String NAME_REGEX = "^.\\w+\\.(" + Format.getRegexFileFormats() + "){1,63}$";

  private FileName(String value) {
    super(value);
  }

  /**
   * Создание класса проверки имени файла.
   *
   * @param value значение имени
   * @return result имя пользователя
   */
  public static Either<Failure, FileName> create(String value) {
    return (!value.matches(NAME_REGEX))
            ? Either.left(new InvalidValues(Map.of(InvalidValueParam.INVALID_ENTITY_NAME, "name")))
            : Either.right(new FileName(value));
  }
}
