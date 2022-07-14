package com.htc.application.dto.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление внутренней ошибки сервера.
 */
public class InternalServerErrorResponse extends AbstractDtoError {

    /**
     * HTTP статус ошибки.
     *
     * @return {@link HttpStatus HTTP статус}.
     */
    private final @Getter HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * Код HTTP статуса для сериализации ошибки.
     *
     * @return Код HTTP статуса.
     */
    private final @Getter int statusCode = httpStatus.value();

    private final static String DEFAULT_MESSAGE = "Внутренняя ошибка сервера";

    public InternalServerErrorResponse() {
        super(DEFAULT_MESSAGE);
    }
}
