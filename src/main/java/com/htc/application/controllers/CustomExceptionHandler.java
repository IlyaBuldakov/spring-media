package com.htc.application.controllers;

import com.htc.application.dtos.exceptions.CustomResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Перехватчик исключений.
 */
@ControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler(CustomResponseStatusException.class)
  @ResponseBody
  ResponseEntity<CustomResponseStatusException> handleEx(CustomResponseStatusException exception) {
    return new ResponseEntity<>(exception, exception.getStatus());
  }
}
