package com.htc.application.dto.errors;

import com.htc.domain.entities.failure.Forbidden;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление ошибки "Нет доступа".
 */
public class ForbiddenResponse extends AbstractDtoError {

  /**
   * HTTP статус ошибки.
   */
  private final @Getter HttpStatus httpStatus = HttpStatus.FORBIDDEN;

  /**
   * Код HTTP статуса для сериализации ошибки.
   */
  private final @Getter int statusCode = httpStatus.value();

  public ForbiddenResponse() {
    super(Forbidden.getMessage());
  }
}
