package ru.kiryanovid.application.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.kiryanovid.domain.entity.errors.Failure;

/**
 *
 */
public class BadRequestDto extends FailureException{
    /**
     * Создаёт экземпляр класса {@link FailureException}.
     *
     * @param failure Исходная ошибка доменного слоя.
     */
    public BadRequestDto(Failure failure) {
        super(HttpStatus.BAD_REQUEST, failure);
    }
}
