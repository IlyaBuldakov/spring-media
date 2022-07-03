package com.htc.domain.entities.utility.parameters;

import com.htc.domain.entities.failures.Failure;
import com.htc.domain.entities.failures.NotFound;
import io.vavr.control.Either;

/**
 *  Класс проверки значения идентификатора.
 */
public class Id extends EntityParameter<Long> {
  private Id(Long value) {
    super(value);
  }

  /**
   * Создание класса проверки идентификатора сущности.
   *
   * @param value значение идентификатора
   * @return result идентификатор сущности
   */
  public static Either<Failure, Id> create(Long value) {
    return (value < 1)
            ? Either.left(NotFound.DEFAULT_MESSAGE)
            : Either.right(new Id(value));
  }
}
