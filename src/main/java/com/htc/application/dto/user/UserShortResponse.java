package com.htc.application.dto.user;

import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Краткое представление сущности пользователя (ответ)
 *
 * @author IlyaBuldakov
 */
public class UserShortResponse {

    public UserShortResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    /**
     * Идентификатор пользователя
     *
     * @return id Идентификатор пользователя
     */
    private final @Getter int id;

    /**
     * Имя пользователя
     *
     * @return name Имя пользователя
     */
    private final @Getter String name;
}
