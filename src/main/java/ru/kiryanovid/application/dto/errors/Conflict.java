package ru.kiryanovid.application.dto.errors;

import org.springframework.http.HttpStatus;

public class Conflict extends FailureException{
    /**
     * Создаёт экземпляр класса {@link FailureException}.
     *
     * @param status  Код статуса.
     * @param message Сообщение ошибки.
     */
    public Conflict(HttpStatus status, String message) {
        super(status, message);
    }
}
