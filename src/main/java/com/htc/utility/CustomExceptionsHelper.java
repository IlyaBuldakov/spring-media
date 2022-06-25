package com.htc.utility;

import com.htc.application.dtos.exceptions.InternalServerErrorResponse;
import com.htc.application.dtos.exceptions.InvalidValuesResponse;
import com.htc.application.dtos.exceptions.NotFoundResponse;
import com.htc.application.dtos.exceptions.UnauthorizedResponse;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.failures.Unauthorized;
import io.vavr.control.Either;

/**
 * Вспомогательный класс для работы с возвращаемыми представлениями ошибок.
 */
public final class CustomExceptionsHelper {
  private CustomExceptionsHelper() {}

  /**
   * Возвращает нужный тип представления ошибки.
   *
   *<p>Убран pattern matching instanceof от
   *Revision number 5b0ff70bf0229a1be1ab4992434609a9156d516d<p/>
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
    if (object instanceof InvalidValues) {
      return new InvalidValuesResponse(object, ((InvalidValues) object).getValues());
    }
    if (object instanceof Unauthorized) {
      return new UnauthorizedResponse(object);
    }
    return new InternalServerErrorResponse(object);
  }
}
