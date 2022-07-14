package com.htc.application.dto.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class InternalServerErrorResponse extends AbstractDtoError {

    private final @Getter HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    private final @Getter int statusCode = httpStatus.value();

    public InternalServerErrorResponse(String message) {
        super(message);
    }
}
