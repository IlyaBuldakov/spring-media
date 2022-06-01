package ru.kiryanovid.domain.entity.errors;

/**
 * Не валидное значение
 */
public class InvalidValue extends Failure{

    public InvalidValue(String message) {
        super(message);
    }
}
