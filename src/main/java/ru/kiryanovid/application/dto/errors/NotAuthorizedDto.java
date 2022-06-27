package ru.kiryanovid.application.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class NotAuthorizedDto {
    /**
     * код статуса ошибки
     */
    @Getter private Integer statusCode;

    /**
     * Текст ошибки
     */
    @Getter private String error;
}
