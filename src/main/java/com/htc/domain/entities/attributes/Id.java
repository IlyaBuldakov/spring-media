package com.htc.domain.entities.attributes;

import com.htc.domain.entities.failures.InvalidValue;
import io.vavr.control.Either;

/**
 * Идентификатор сущности.
 */
public final class Id extends Attribute<Integer> {
  /**
   * Проверяет входные данные на корректность и создаёт идентификатор сущности.
   * Идентификатор должен быть неотрицательным числом.
   *
   * @param value Входные данные.
   * @return Идентификатор сущности.
   */
  public static Either<InvalidValue, Id> create(int value) {
    if (value < 0) {
      return Either.left(InvalidValue.INVALID_ENTITY_ID);
    }

    return Either.right(new Id(value));
  }

  private Id(int value) {
    super(value);
  }
}
