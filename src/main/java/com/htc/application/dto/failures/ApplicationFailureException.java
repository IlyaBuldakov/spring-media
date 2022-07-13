package com.htc.application.dto.failures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Общий класс ошибок слоя приложения.
 */
@JsonIgnoreProperties(value = {
    "cause", "stackTrace", "suppressed", "localizedMessage" })
public abstract class ApplicationFailureException extends RuntimeException {
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

  /**
   * Возвращает код статуса.
   *
   * @return statusCode Код статуса.
   */
  @JsonProperty("statusCode")
  private int getStatusCode() {
    return status.value();
  }

  /**
   * Создаёт экземпляр класса {@link ApplicationFailureException}.
   *
   * @param status Код статуса.
   * @param message Сообщение ошибки.
   */
  public ApplicationFailureException(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
}
