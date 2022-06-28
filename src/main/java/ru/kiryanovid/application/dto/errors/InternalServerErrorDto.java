package ru.kiryanovid.application.dto.errors;

import org.springframework.http.HttpStatus;
import ru.kiryanovid.domain.entity.errors.Failure;

/**
 *
 */

public class InternalServerErrorDto extends FailureException{
    /**
     * Создаёт экземпляр класса {@link FailureException}.
     *
     * @param failure Исходная ошибка доменного слоя.
     */

    public InternalServerErrorDto(Failure failure) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, failure);
    }
}
