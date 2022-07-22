package com.htc.application.dto.errors;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.htc.domain.entities.failures.InvalidValue;
import com.htc.domain.entities.failures.InvalidValuesContainer;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Представление ошибки невалидного запроса.
 */
@JsonPropertyOrder(value = {"statusCode", "message", "problems"})
public class BadRequestResponse extends AbstractDtoError {

    /**
     * HTTP статус ошибки.
     */
    private final @Getter HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    /**
     * Код HTTP статуса для сериализации ошибки.
     */
    private final @Getter int statusCode = httpStatus.value();

    /**
     * Список ошибок {@link InvalidValueResponse}, вызвавших BadRequest.
     */
    private final @Getter List<InvalidValueResponse> problems;

    /**
     * Стандартное сообщение ошибки.
     */
    private final static String DEFAULT_MESSAGE = "Невалидный запрос";

    /**
     * Конструктор из контейнера ошибок InvalidValue (невалидных полей).
     *
     * @param invalidValues {@link InvalidValuesContainer Контейнер невалидных полей}.
     */
    public BadRequestResponse(InvalidValuesContainer invalidValues) {
        super(DEFAULT_MESSAGE);
        this.problems = invalidValues
                .getInvalidValues()
                .stream()
                .map(InvalidValueResponse::new)
                .toList();
    }

    /**
     * Конструктор из единственной ошибки InvalidValue (невалидного поля).
     *
     * @param invalidValue {@link InvalidValue Ошибка невалидного поля}.
     */
    public BadRequestResponse(InvalidValue invalidValue) {
        super(DEFAULT_MESSAGE);
        this.problems = List.of(new InvalidValueResponse(invalidValue));
    }
}
