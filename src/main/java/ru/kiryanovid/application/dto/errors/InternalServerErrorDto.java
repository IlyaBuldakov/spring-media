package ru.kiryanovid.application.dto.errors;

import org.springframework.http.HttpStatus;
import ru.kiryanovid.domain.entity.errors.Failure;

/**
 * Представление сущности ошибки сервера.
 */

public class InternalServerErrorDto extends FailureException {
  /**
  * Сообщение по умолчанию.
  */
  private static final String DEFAULT_MESSAGE = "Запрос задачи содержит некорректные значения.";

  /**
  * Создаёт экземпляр класса {@link InternalServerErrorDto}.
  */
  public InternalServerErrorDto() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
  }

  public InternalServerErrorDto(HttpStatus status, String message) {
    super(status, message);
  }

  /**
  * Создаёт экземпляр класса {@link InternalServerErrorDto}.
  *
  * @param failure Ошибка доменного слоя.
  */
  public InternalServerErrorDto(Failure failure) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, failure.getMessage());
  }
}
