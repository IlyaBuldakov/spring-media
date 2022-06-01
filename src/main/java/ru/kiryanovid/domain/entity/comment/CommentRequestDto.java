package ru.kiryanovid.domain.entity.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class CommentRequestDto {
    /**
     * Идентификатор автора
     */
    @Getter private Integer user;

    /**
     * Идентификатор задачи
     */
    @Getter private Integer task;

    /**
     * Сообщение
     */
    @Getter private String message;

}
