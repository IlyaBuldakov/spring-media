package com.htc.domain.entities.failures;

import lombok.Getter;

import java.util.LinkedList;

/**
 * Класс, содержащий связку InvalidValue.
 */
public class InvalidValuesContainer implements Failure {

    /**
     * Список ошибок для использования в BadRequest
     * (см. спецификацию).
     */
    private final @Getter LinkedList<InvalidValue> invalidValues = new LinkedList<>();

    /**
     * Добавление InvalidValue в {@link InvalidValuesContainer#getInvalidValues() список}.
     *
     * @param invalidValue Ошибка.
     */
    public void addInvalidValue(InvalidValue invalidValue) {
        invalidValues.add(invalidValue);
    }

    /**
     * Слияние контейнеров {@link InvalidValue}.
     *
     * @param container Другой контейнер.
     */
    public void merge(InvalidValuesContainer container) {
        invalidValues.addAll(container.invalidValues);
    }
}
