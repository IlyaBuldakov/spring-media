package com.htc.application.exceptionhandlers;

import com.fasterxml.jackson.core.JacksonException;
import com.htc.application.dtos.exceptions.CustomResponseStatusException;
import com.htc.application.dtos.exceptions.InvalidValuesResponse;
import com.htc.domain.entities.failures.InvalidValueParam;
import com.htc.domain.entities.failures.InvalidValues;
import java.util.Map;
import javax.annotation.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Перехватчик исключений преобразования типов.
 */
@Priority(1)
@ControllerAdvice
class CastExceptionHandler {
  protected final Logger logger = LoggerFactory.getLogger(CastExceptionHandler.class);

  @ExceptionHandler({IllegalArgumentException.class, JacksonException.class})
  ResponseEntity<CustomResponseStatusException> illegalArgumentEx(Exception exception) {
    logger.error(this.getClass().getTypeName(), exception);
    var invalidValues = new InvalidValues(Map.of(
            InvalidValueParam.DEFAULT_MESSAGE, "Параметр пути")
    );
    var invalidValuesResponse = new InvalidValuesResponse(invalidValues, invalidValues.getValues());
    return new ResponseEntity<>(invalidValuesResponse, invalidValuesResponse.getStatus());
  }
}
