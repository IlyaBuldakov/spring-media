package com.htc.application.dto.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundResponse extends AbstractDtoError {

    private final @Getter HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    private final @Getter int statusCode = httpStatus.value();

    public NotFoundResponse(String message) {
        super(message);
    }
}
