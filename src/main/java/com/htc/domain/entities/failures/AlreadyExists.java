package com.htc.domain.entities.failures;

/**
 * Ошибка "Уже существует".
 */
public class AlreadyExists extends Failure {

    public AlreadyExists(String message) {
        super(message);
    }
}
