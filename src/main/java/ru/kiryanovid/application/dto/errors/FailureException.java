package ru.kiryanovid.application.dto.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.kiryanovid.domain.entity.errors.Failure;

public abstract class FailureException extends RuntimeException{
    /**
     * Код статуса.
     *
     * @return statusCode Код статуса.
     */

    private final @Getter HttpStatus status;

    /**
     * Код статуса.
     *
     * @return statusCode Код статуса.
     */
    private final @Getter int statusCode;

    /**
     * Сообщение ошибки.
     *
     * @return message Сообщение ошибки.
     */
    private final @Getter String message;

    /**
     * Создаёт экземпляр класса {@link FailureException}.
     *
     * @param status Код статуса.
     * @param failure Исходная ошибка доменного слоя.
     */
    public FailureException(HttpStatus status, Failure failure) {
        this.status = status;
        this.statusCode = status.value();
        this.message = failure.getMessage();
    }
}
