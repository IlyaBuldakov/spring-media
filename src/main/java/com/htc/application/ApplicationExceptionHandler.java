package com.htc.application;

import com.fasterxml.jackson.core.JacksonException;
import com.htc.application.dto.failures.ApplicationFailureException;
import com.htc.application.dto.failures.BadRequestResponse;
import com.htc.application.dto.failures.InternalServerErrorResponse;
import com.htc.domain.entities.failures.InvalidValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Общий для всех контроллеров обработчик исключений.
 */
@ControllerAdvice
public class ApplicationExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

  @ExceptionHandler(ApplicationFailureException.class)
  ResponseEntity<ApplicationFailureException> handleAuth(ApplicationFailureException exception) {
    logger.error("ApplicationExceptionHandler.handleAuth()", exception);
    return new ResponseEntity<>(exception, exception.getStatus());
  }

  @ExceptionHandler({
      IllegalArgumentException.class,
      JacksonException.class
  })
  ResponseEntity<ApplicationFailureException> handleIllegalArgument(
      Exception exception) {
    logger.error("ApplicationExceptionHandler.handleIllegalArgument()", exception);

    var domainFailure = new InvalidValues();
    var applicationFailure = new BadRequestResponse(domainFailure);
    return new ResponseEntity<>(applicationFailure, applicationFailure.getStatus());
  }

  @ExceptionHandler(Throwable.class)
  ResponseEntity<ApplicationFailureException> handleAll(Throwable throwable) {
    logger.error("ApplicationExceptionHandler.handleAll()", throwable);

    var failure = new InternalServerErrorResponse();
    return new ResponseEntity<>(failure, failure.getStatus());
  }
}
