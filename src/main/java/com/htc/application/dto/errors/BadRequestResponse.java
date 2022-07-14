package com.htc.application.dto.errors;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.htc.domain.entities.failures.InvalidValuesContainer;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonPropertyOrder(value = {"statusCode", "message", "problems"})
public class BadRequestResponse extends AbstractDtoError {

    private final @Getter HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    private final @Getter int statusCode = httpStatus.value();

    private final @Getter List<InvalidValueResponse> problems;

    private final static String DEFAULT_MESSAGE = "Невалидный запрос";

    public BadRequestResponse(InvalidValuesContainer invalidValues) {
        super(DEFAULT_MESSAGE);
        this.problems = invalidValues
                .getInvalidValues()
                .stream()
                .map(InvalidValueResponse::new)
                .toList();
    }
}
