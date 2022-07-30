package com.htc.application.dto.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Базовые класс представлений ошибок.
 */
@AllArgsConstructor
public class HttpError {
  public static HttpError forbidden(String message) {
    return new HttpError(HttpStatus.FORBIDDEN, message);
  }

  public static HttpError notFound(String message) {
    return new HttpError(HttpStatus.NOT_FOUND, message);
  }

  public static HttpError internalServerError(String message) {
    return new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }


  /**
   * Код статуса.
   *
   * @return statusCode Код статуса.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter @JsonIgnore HttpStatus status;

  /**
   * Сообщение ошибки.
   *
   * @return message Сообщение ошибки.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter String message;

  public ResponseEntity<Object> toResponseEntity() {
    return new ResponseEntity<>(this, status);
  }

  /**
   * Возвращает код статуса.
   *
   * @return statusCode Код статуса.
   */
  @JsonProperty("statusCode")
  private int getStatusCode() {
    return status.value();
  }
}
