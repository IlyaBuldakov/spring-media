package ru.kiryanovid.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kiryanovid.application.dto.errors.FailureException;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler({FailureException.class})
    public static ResponseEntity<FailureException> handleException(FailureException exception) {
        return new ResponseEntity<>(exception, exception.getStatus());
    }
}
