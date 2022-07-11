package com.htc.domain.entities.utility.parameters.file;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.EntityParameter;
import io.vavr.control.Either;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

/**
 *  Класс проверки содержания файла.
 */
public class FileBody extends EntityParameter<String> {
  private FileBody(String value) {
    super(value);
  }

  /**
   * Создание класса проверки содержания файла.
   *
   * @param value значение содержания файла в виде base64
   * @return result содержание файла
   */
  public static Either<Failure, FileBody> create(String value) {
    return (!(Base64.isBase64(value) && (value.length() > 0)))
            ? Either.left(new InvalidValues(Map.of(InvalidValueParam.INVALID_FILE_BODY, "file")))
            : Either.right(new FileBody(value));
  }
}
