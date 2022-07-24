package com.htc.application;

import com.fasterxml.jackson.core.JacksonException;
import com.htc.application.dto.responsestatus.ApplicationFailureException;
import com.htc.application.dto.responsestatus.BadRequestResponse;
import com.htc.domain.entities.failures.InvalidValues;
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

  @ExceptionHandler({
      IllegalArgumentException.class,
      JacksonException.class
  })
  ResponseEntity<ApplicationFailureException> handleIllegalArgument(
          Exception exception) {

    InvalidValues invalidValues = new InvalidValues();
    var applicationFailure = new BadRequestResponse(invalidValues);
    return new ResponseEntity<>(applicationFailure, applicationFailure.getStatus());
  }
}
