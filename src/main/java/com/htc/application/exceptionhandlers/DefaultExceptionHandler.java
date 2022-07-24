package com.htc.application.exceptionhandlers;

import javax.annotation.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Перехватчик исключений по умолчанию.
 */
@Priority(255)
@ControllerAdvice
class DefaultExceptionHandler {
  protected final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

  @ExceptionHandler(Throwable.class)
  ResponseEntity<RuntimeException> handleAllEx(Throwable throwable) {
    logger.error(this.getClass().getTypeName(), throwable);
    throw new RuntimeException(throwable.getMessage(), throwable.getCause());
  }
}
