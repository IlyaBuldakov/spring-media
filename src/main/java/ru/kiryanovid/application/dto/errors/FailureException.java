package ru.kiryanovid.application.dto.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.kiryanovid.domain.entity.errors.Failure;

@JsonIgnoreProperties(value = {
        "cause", "stackTrace", "suppressed", "localizedMessage", "status" })
public abstract class FailureException extends RuntimeException{
    /**
     * Код статуса.
     *
     * @return statusCode Код статуса.
     */

    private final @Getter HttpStatus status;

    /**
     * Сообщение ошибки.
     *
     * @return message Сообщение ошибки.
     */
    private final @Getter String message;
    /**
     * Возвращает код статуса.
     *
     * @return statusCode Код статуса.
     */
    @JsonProperty("statusCode")
    private int getStatusCode() {
        return status.value();
    }

    /**
     * Создаёт экземпляр класса {@link FailureException}.
     *
     * @param status Код статуса.
     * @param message Сообщение ошибки.
     */
    public FailureException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
