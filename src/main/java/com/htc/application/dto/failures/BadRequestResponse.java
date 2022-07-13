package com.htc.application.dto.failures;

import com.htc.domain.entities.failures.InvalidValues;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Представление сущности пользователя (ответ на запрос).
 */
public class BadRequestResponse extends ApplicationFailureException {
  /**
   * Связанное с ошибкой поля интерфейса.
   *
   * @return message Сообщение ошибки.
   */
  @SuppressWarnings("JavadocDeclaration")
  private final @Getter Iterable<FieldInvalidResponse> problems;

  /**
   * Создаёт экземпляр класса {@link BadRequestResponse}.
   *
   * @param invalidValues Некорректные значения.
   */
  public BadRequestResponse(InvalidValues invalidValues) {
    super(HttpStatus.BAD_REQUEST, invalidValues.getMessage());

    this.problems = invalidValues.invalidValues()
        .entrySet()
        .stream()
        .map(entry -> new FieldInvalidResponse(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }
}
