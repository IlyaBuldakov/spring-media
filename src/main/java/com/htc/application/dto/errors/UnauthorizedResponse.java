package com.htc.application.dto.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление ошибки авторизации.
 */
public class UnauthorizedResponse extends AbstractDtoError {

    /**
     * HTTP статус ошибки.
     */
    private final @Getter HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    /**
     * Код HTTP статуса для сериализации ошибки.
     */
    private final @Getter int statusCode = httpStatus.value();

    public UnauthorizedResponse(String message) {
        super(message);
    }
}
