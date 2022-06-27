package ru.kiryanovid.application.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.kiryanovid.domain.entity.errors.Failure;

/**
 *
 */

public class InternalServerErrorDto extends FailureException{

    public InternalServerErrorDto(Failure failure) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, failure);
    }
}
