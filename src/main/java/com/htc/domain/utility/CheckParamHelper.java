package com.htc.domain.utility;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.failures.NotFound;
import io.vavr.control.Either;
import java.util.Map;
import org.apache.commons.validator.routines.IntegerValidator;

/**
 * Вспомогательный класс для проверки параметров.
 */
public final class CheckParamHelper {

  private CheckParamHelper() {}

  /**
   * Проверка преобразования идентификатора.
   *
   *<p>Идентификатор должен быть числом.<p/>
   *
   * @param id строковое представление идентификатора
   *
   * @return id числовое представление идентификатора
   */
  public static Either<Failure, Integer> checkId(String id) {
    if (!IntegerValidator.getInstance().isValid(id)) {
      return Either.left(new InvalidValues(Map.of(InvalidValueParam.INVALID_ENTITY_ID, "id")));
    }
    var userId = Integer.parseInt(id);
    return (userId < 1)
      ? Either.left(NotFound.DEFAULT_MESSAGE)
      : Either.right(userId);
  }
}
