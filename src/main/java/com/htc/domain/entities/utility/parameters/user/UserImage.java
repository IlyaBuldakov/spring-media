package com.htc.domain.entities.utility.parameters.user;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.utility.parameters.EntityParameter;
import io.vavr.control.Either;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

/**
 *  Класс проверки значения изображения пользователя.
 */
public class UserImage extends EntityParameter<String> {
  private UserImage(String value) {
    super(value);
  }

  /**
   * Создание класса проверки изображения пользователя.
   *
   * @param value значение изображения в виде base64
   * @return result изображение пользователя
   */
  public static Either<Failure, UserImage> create(String value) {
    return (!(Base64.isBase64(value) && (value.length() > 0)))
            ? Either.left(new InvalidValues(Map.of(InvalidValueParam.INVALID_USER_IMAGE, "image")))
            : Either.right(new UserImage(value));
  }
}
