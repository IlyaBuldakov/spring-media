package com.htc.application.controllers;

import com.htc.application.dto.responsestatus.ApplicationFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Общий для всех контроллеров обработчик исключений.
 */
@ControllerAdvice
public class ApplicationExceptionHandler {
  @ExceptionHandler(ApplicationFailureException.class)
  ResponseEntity<ApplicationFailureException> handleAuth(ApplicationFailureException exception) {
    return new ResponseEntity<>(exception, exception.getStatus());
  }
}
