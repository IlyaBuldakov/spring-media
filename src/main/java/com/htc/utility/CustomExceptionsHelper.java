package com.htc.utility;

import com.htc.application.dtos.exceptions.NotFoundResponse;
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
   * @param <L> type тип параметра левой части
   * @param <R> type тип параметра правой части
   *
   * @return exceptionResponse представление ошибки
   */
  public static <L, R> RuntimeException getExceptionFromLeft(Either<L, R> result) {
    Object object = result.getLeft();
    if (object instanceof NotFound notFound) {
      return new NotFoundResponse(notFound);
    }
    return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
