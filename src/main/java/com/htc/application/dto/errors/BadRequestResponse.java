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
     *
     * @return {@link HttpStatus HTTP статус}.
     */
    private final @Getter HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    /**
     * Код HTTP статуса для сериализации ошибки.
     *
     * @return Код HTTP статуса.
     */
    private final @Getter int statusCode = httpStatus.value();

    /**
     * Список ошибок {@link InvalidValueResponse}, вызвавших BadRequest.
     *
     * @return Список ошибок.
     */
    private final @Getter List<InvalidValueResponse> problems;

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

    public BadRequestResponse(InvalidValue invalidValue) {
        super(DEFAULT_MESSAGE);
        this.problems = List.of(new InvalidValueResponse(invalidValue));
    }
}
