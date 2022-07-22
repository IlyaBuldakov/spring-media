package com.htc.application.services;

import com.htc.application.dto.errors.BadRequestResponse;
import com.htc.application.dto.errors.InternalServerErrorResponse;
import com.htc.application.dto.errors.NotFoundResponse;
import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.failures.InvalidValuesContainer;
import com.htc.domain.entities.failures.NotFound;

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
            case InvalidValue invalidValue ->
                    exception = new BadRequestResponse(invalidValue);
            case NotFound notFound ->
                    exception = new NotFoundResponse(notFound);
            default -> throw new InternalServerErrorResponse();
        }
        return exception;
    }
}
