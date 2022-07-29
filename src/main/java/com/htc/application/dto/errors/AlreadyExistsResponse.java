package com.htc.application.dto.errors;

import com.htc.domain.entities.failures.AlreadyExists;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AlreadyExistsResponse extends AbstractDtoError {

    /**
     * HTTP статус ошибки.
     */
    private final @Getter HttpStatus httpStatus = HttpStatus.CONFLICT;

    /**
     * Код HTTP статуса для сериализации ошибки.
     */
    private final @Getter int statusCode = httpStatus.value();

    /**
     * Конструктор из {@link AlreadyExists соответствующей сущности}
     *
     * @param alreadyExists Сущность ошибки "Уже существует".
     */
    public AlreadyExistsResponse(AlreadyExists alreadyExists) {
        super(alreadyExists.getMessage());
    }

    public AlreadyExistsResponse(String message) {
        super(message);
    }
}
