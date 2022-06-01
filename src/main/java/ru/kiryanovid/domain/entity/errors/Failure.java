package ru.kiryanovid.domain.entity.errors;

import lombok.Getter;

/**
 * Ошибка.
 */
public abstract class Failure {
    private final @Getter
    String message;

    public Failure(String message) {
        this.message = message;
    }
}
