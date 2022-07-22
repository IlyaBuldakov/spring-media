package finalproject.application.controllers;

import finalproject.application.dto.failures.FailureDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Перехватчик генерируемых исключений Runtime Exception.
 */
@ControllerAdvice
public class ExceptionsHandler {

  @ExceptionHandler(FailureDto.class)
  public ResponseEntity<FailureDto> handleException(FailureDto e) {
    return new ResponseEntity<>(e, e.getStatus());
  }
}
