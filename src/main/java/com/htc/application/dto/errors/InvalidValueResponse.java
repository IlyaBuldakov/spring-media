package com.htc.application.dto.errors;

import com.htc.domain.entities.failures.InvalidValue;
import lombok.Getter;

public class InvalidValueResponse {

    private final @Getter String field;

    private final @Getter String message;

    public InvalidValueResponse(InvalidValue invalidValue) {
        this.field = invalidValue.getField();
        this.message = invalidValue.getMessage();
    }
}
