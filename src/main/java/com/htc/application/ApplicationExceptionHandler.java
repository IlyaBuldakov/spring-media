package com.htc.application;

import com.htc.application.dto.errors.AbstractDtoError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(AbstractDtoError.class)
    public ResponseEntity<AbstractDtoError> handleBadRequest(AbstractDtoError exception) {
        return new ResponseEntity<>(exception, exception.getHttpStatus());
    }
}
