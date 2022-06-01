package ru.kiryanovid.application.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class UserBasicDto {
    /**
     * Идентификатор пользователя
     *
     */
    @Getter private Integer id;

    /**
     * Имя пользователя
     */
    @Getter private String name;
}
