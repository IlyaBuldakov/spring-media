package ru.kiryanovid.application.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 *
 */
public class FieldMessageDtoTemp extends FailureException{
    /**
     * Связанное с ошибкой поле интерфейса
     */
    @Getter private String field;

    /**
     * Создаёт экземпляр класса {@link FailureException}.
     *
     * @param status  Код статуса.
     * @param message Сообщение ошибки.
     */
    public FieldMessageDtoTemp(HttpStatus status, String message) {
        super(status, message);
    }
}
