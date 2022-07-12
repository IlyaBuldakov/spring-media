package com.htc.domain.entities.failures;

import lombok.Getter;

/**
 * Ошибка "Не найдено".
 */
public enum NotFound implements Failure {

    USER("Пользователь не существует");

    NotFound(String message) {
        this.message = message;
    }

    /**
     * Сообщение об ошибке.
     *
     * @return Сообщение об ошибке.
     */
    private final @Getter String message;
}
