package com.htc.domain.entities.failures;

import lombok.Getter;

/**
 * Ошибка "Уже существует".
 */
public enum AlreadyExists implements Failure {

    /**
     * Стандартное сообщение.
     */
    DEFAULT_MESSAGE("Уже существует");

    AlreadyExists(String message) {
        this.message = message;
    }

    /**
     * Сообщение об ошибке.
     */
    private final @Getter String message;
}
