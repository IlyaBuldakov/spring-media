package com.htc.application.dto.errors;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление ошибки 400 Bad Request.
 */
public class BadRequest extends HttpError {
  /**
   * Связанное с ошибкой поля интерфейса.
   *
   * @return message Сообщение ошибки.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Collection<FieldInvalid> problems;

  public BadRequest(String message, Collection<FieldInvalid> problems) {
    super(HttpStatus.BAD_REQUEST, message);
    this.problems = problems;
  }

  public BadRequest(String message, Supplier<Collection<FieldInvalid>> problemsSupplier) {
    this(message, problemsSupplier.get());
  }

  public BadRequest(String message) {
    this(message, Collections.emptyList());
  }

  /**
   *  Представление ошибки в поле ввода.
   *
   * @param message Сообщение ошибки.
   * @param field Поле ввода, связанное с ошибкой.
   */
  public record FieldInvalid(
      String message,
      String field
  ) {}
}
