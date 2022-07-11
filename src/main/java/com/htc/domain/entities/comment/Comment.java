package com.htc.domain.entities.comment;

import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Класс, описывающий сущность комментария.
 */
@AllArgsConstructor
public class Comment {

    /**
     * Идентификатор комментария.
     *
     * @return Идентификатор комментария.
     */
    private @Getter int id;

    /**
     * Дата создания комментария.
     *
     * @return Дата создания.
     */
    private @Getter LocalDate date;

    /**
     * Автор комментария.
     *
     * @return Автор комментария.
     */
    private @Getter User user;

    /**
     * Содержимое комментария.
     *
     * @return Сообщение комментария.
     */
    private @Getter String message;
}
