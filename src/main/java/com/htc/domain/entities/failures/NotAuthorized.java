package com.htc.domain.entities.failures;

import lombok.Getter;

/**
 * Ошибка авторизации.
 */
public enum NotAuthorized implements Failure {

    DEFAULT_MESSAGE("Ошибка авторизации");

    NotAuthorized(String message) {
        this.message = message;
    }

    /**
     * Сообщение об ошибке.
     *
     * @return Сообщение об ошибке.
     */
    private final @Getter String message;
}
