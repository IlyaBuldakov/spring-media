package com.htc.domain.entities.failures;

import java.util.ArrayList;

/**
 * Некорректные значения.
 */
public class InvalidValues implements Failure {
  /**
   * Сообщение по умолчанию.
   */
  private static final String DEFAULT_MESSAGE =
          "Запрос пользователя содержит некорректные значения.";

  /**
   * Список некорректных значений.
   */
  private final ArrayList<InvalidValue> invalidValues;

  @Override
  public String getMessage() {
    return DEFAULT_MESSAGE;
  }

  public ArrayList<InvalidValue> getInvalidValues() {
    return invalidValues;
  }

  /**
   * Добавляет ошибку в список.
   *
   * @param invalidValue Ошибка поля сущности.
   */
  public void addInvalidValue(InvalidValue invalidValue) {
    if (invalidValue == null) {
      return;
    }
    invalidValues.add(invalidValue);
  }

  public InvalidValues() {
    this.invalidValues = new ArrayList<>();
  }
}
