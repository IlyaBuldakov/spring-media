package com.htc.application.dto.errors;

import com.htc.domain.entities.failures.NotFound;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление ошибки поиска ("не найдено").
 */
public class NotFoundResponse extends AbstractDtoError {

  /**
   * HTTP статус ошибки.
   */
  private final @Getter HttpStatus httpStatus = HttpStatus.NOT_FOUND;

  /**
   * Код HTTP статуса для сериализации ошибки.
   */
  private final @Getter int statusCode = httpStatus.value();

  /**
   * Конструктор из {@link NotFound соответствующей сущности}
   *
   * @param notFound Сущность ошибки поиска "не найдено".
   */
  public NotFoundResponse(NotFound notFound) {
    super(notFound.getMessage());
  }
}
