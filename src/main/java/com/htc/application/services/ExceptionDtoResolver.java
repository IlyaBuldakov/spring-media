package com.htc.application.services;

import com.htc.application.dto.errors.AlreadyExistsResponse;
import com.htc.application.dto.errors.BadRequestResponse;
import com.htc.application.dto.errors.ForbiddenResponse;
import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.dto.errors.NotFoundResponse;
import com.htc.application.dto.errors.UnauthorizedResponse;
import com.htc.domain.entities.failure.AlreadyExists;
import com.htc.domain.entities.failure.Failure;
import com.htc.domain.entities.failure.InvalidValue;
import com.htc.domain.entities.failure.InvalidValuesContainer;
import com.htc.domain.entities.failure.NotFound;
import com.htc.domain.entities.failure.Unauthorized;
import com.htc.domain.entities.failure.Forbidden;

/**
 * Класс, объединяющий в себе логику преобразования
 * сущностей ошибок (failure) в представления реальных исключений (dto).
 */
public class ExceptionDtoResolver {

  /**
   * Преобразование сущности ошибки в exception.
   *
   * @param failure Сущность ошибки.
   * @return Исключение, соответствующее {@link Failure}.
   */
  public static RuntimeException resolve(Failure failure) {
    RuntimeException exception;
    switch (failure) {
      case InvalidValuesContainer invalidValues ->
              exception = new BadRequestResponse(invalidValues);
      case Unauthorized unauthorized ->
              exception = new UnauthorizedResponse(unauthorized.getMessage());
      case Forbidden forbidden ->
              exception = new ForbiddenResponse();
      case InvalidValue invalidValue ->
              exception = new BadRequestResponse(invalidValue);
      case NotFound notFound ->
              exception = new NotFoundResponse(notFound);
      case AlreadyExists alreadyExists ->
              exception = new AlreadyExistsResponse(alreadyExists);
      default -> throw new InternalServerErrorResponse();
    }
    return exception;
  }
}
