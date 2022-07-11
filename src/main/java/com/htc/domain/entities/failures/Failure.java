package com.htc.domain.entities.failures;

import lombok.Getter;

/**
 * Класс, описывающий сущность ошибки.
 */
public abstract class Failure {

    /**
     * Сообщение ошибки.
     *
     * @return Сообщение ошибки.
     */
    private final @Getter String message;

    public Failure(String message) {
        this.message = message;
    }
}
