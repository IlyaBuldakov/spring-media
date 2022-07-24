package com.htc.application.exceptionhandlers;

import com.htc.application.dtos.exceptions.CustomResponseStatusException;
import javax.annotation.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Перехватчик исключений типа Failure.
 */
@Priority(0)
@ControllerAdvice
class FailureExceptionHandler {
  protected final Logger logger = LoggerFactory.getLogger(FailureExceptionHandler.class);

  @ExceptionHandler(CustomResponseStatusException.class)
  @ResponseBody
  ResponseEntity<CustomResponseStatusException> handleEx(CustomResponseStatusException exception) {
    logger.error(this.getClass().getTypeName(), exception);
    return new ResponseEntity<>(exception, exception.getStatus());
  }
}
