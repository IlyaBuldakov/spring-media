package com.htc.domain.entities.utility.parameters.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.EntityParameter;
import io.vavr.control.Either;
import java.util.Map;

/**
 *  Класс проверки значения имени пользователя.
 */
public class FileUrlPath extends EntityParameter<String> {
  /**
   * <pre>Требования к пути файла.
   * Длина 3-20 символов (включительно).</pre>
   */
  private static final String URL_PATH_REGEX = "^.*.{1,127}$";

  private FileUrlPath(String value) {
    super(value);
  }

  /**
   * Создание класса проверки имени пользователя.
   *
   * @param value значение имени
   * @return result имя пользователя
   */
  public static Either<Failure, FileUrlPath> create(String value) {
    return (!value.matches(URL_PATH_REGEX))
            ? Either.left(new InvalidValues(Map.of(
                    InvalidValueParam.INVALID_FILE_URL_PATH, "filePath")))
            : Either.right(new FileUrlPath(value));
  }
}
