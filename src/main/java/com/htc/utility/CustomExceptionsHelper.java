package com.htc.utility;

import com.htc.application.dtos.exceptions.NotFoundResponse;
import com.htc.domain.entities.failures.Failure;
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
   * @param <T> type тип параметра правой части
   *
   * @return exceptionResponse представление ошибки
   */
  public static <T> RuntimeException getException(Either<Failure, T> result) {
    Failure failure = result.getLeft();
    if (failure instanceof NotFound) {
      return new NotFoundResponse(failure);
    }
    return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, failure.getMessage());
  }
}
