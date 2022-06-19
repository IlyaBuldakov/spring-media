package com.htc.utility;

import com.htc.application.dtos.exceptions.InvalidValueParamResponse;
import com.htc.application.dtos.exceptions.InvalidValuesResponse;
import com.htc.application.dtos.exceptions.NotFoundResponse;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.failures.NotFound;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Вспомогательный класс для работы с возвращаемыми представлениями ошибок.
 */
public final class CustomExceptionsHelper {
  private CustomExceptionsHelper() {}

  /**
   * Возвращает нужный тип представления ошибки.
   *
   * @param result передаваемый тип
   * @param <R> type тип параметра правой части
   *
   * @return exceptionResponse представление ошибки
   */
  public static <R> RuntimeException getExceptionFromLeft(Either<Failure, R> result) {
    Failure object = result.getLeft();
    if (object instanceof NotFound) {
      return new NotFoundResponse(object);
    }
    if (object instanceof InvalidValueParam) {
      return new InvalidValueParamResponse(object, ((InvalidValueParam) object).getField());
    }
    if (object instanceof InvalidValues) {
      return new InvalidValuesResponse(object, ((InvalidValues) object).getValues());
    }
    return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
