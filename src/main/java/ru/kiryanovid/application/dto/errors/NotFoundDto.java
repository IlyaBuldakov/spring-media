package ru.kiryanovid.application.dto.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.kiryanovid.domain.entity.errors.Failure;

/**
 *
 */
public class NotFoundDto extends FailureException{


    /**
     * Создаёт экземпляр класса {@link FailureException}.
     *
     * @param failure Исходная ошибка доменного слоя.
     */
    public NotFoundDto( Failure failure) {
        super(HttpStatus.NOT_FOUND, failure);
    }
}
