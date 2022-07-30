package com.htc.application;

import com.htc.application.dto.errors.BadRequest;
import com.htc.application.dto.errors.HttpError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Общий для всех контроллеров обработчик исключений.
 */
@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Object> handleValidation(MethodArgumentNotValidException exception) {
    log.error("Запрос содержит некорректные данные.", exception);

    final var problems = exception
        .getFieldErrors()
        .stream()
        .map(objectError -> new BadRequest.FieldInvalid(
                objectError.getDefaultMessage(),
                objectError.getField()
            )
        )
        .toList();

    return new BadRequest("Запрос содержит некорректные данные.", problems)
        .toResponseEntity();
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  ResponseEntity<Object> handleInvalidRequestBody(HttpMessageNotReadableException exception) {
    log.error("Тело запроса некорректно.", exception);

    return new BadRequest("Тело запроса некорректно.")
        .toResponseEntity();
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<Object> handleAll(Exception exception) {
    log.error("Внутренняя ошибка сервера.", exception);

    return HttpError.internalServerError(
        "Внутренняя ошибка сервера."
    ).toResponseEntity();
  }
}

