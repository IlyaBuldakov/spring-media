package com.htc.application.exceptionhandlers;

import com.htc.application.dtos.exceptions.CustomResponseStatusException;
import javax.annotation.Priority;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Перехватчик исключений типа Failure.
 */
@Priority(0)
@ControllerAdvice
class FailureExceptionHandler extends CustomExceptionHandler {
  @ExceptionHandler(CustomResponseStatusException.class)
  @ResponseBody
  ResponseEntity<CustomResponseStatusException> handleEx(CustomResponseStatusException exception) {
    logger.error(this.getClass().getTypeName(), exception);
    return new ResponseEntity<>(exception, exception.getStatus());
  }
}
