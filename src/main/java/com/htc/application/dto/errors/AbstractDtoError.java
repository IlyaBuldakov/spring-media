package com.htc.application.dto.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(value = {"cause", "stackTrace", "suppressed", "localizedMessage", "httpStatus"})
public abstract class AbstractDtoError extends RuntimeException {

    public abstract HttpStatus getHttpStatus();

    public AbstractDtoError(String message) {
        super(message);
    }
}
