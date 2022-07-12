package com.htc.application.dto.responsestatus;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.InvalidValues;
import java.util.Collections;
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
   * @param failure Некорректные значения.
   */
  public BadRequestResponse(Failure failure) {
    super(HttpStatus.BAD_REQUEST, failure.getMessage());
    System.out.println("BadRequestResponse created");
    if (failure instanceof InvalidValues invalidValues) {
      this.problems = invalidValues.getInvalidValues()
              .stream()
              .map(FieldInvalidResponse::new)
              .collect(Collectors.toList());
    } else {

      this.problems = Collections.emptyList();
    }
  }
}