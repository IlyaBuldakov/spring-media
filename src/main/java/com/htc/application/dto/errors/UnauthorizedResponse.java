package com.htc.application.dto.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UnauthorizedResponse extends AbstractDtoError {

    private final @Getter HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    private final @Getter int statusCode = httpStatus.value();

    public UnauthorizedResponse(String message) {
        super(message);
    }
}
