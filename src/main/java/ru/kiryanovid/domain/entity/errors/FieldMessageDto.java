package ru.kiryanovid.domain.entity.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class FieldMessageDto {
    /**
     *
     */
    @Getter private String field;

    /**
     *
     */
    @Getter private String message;
}
