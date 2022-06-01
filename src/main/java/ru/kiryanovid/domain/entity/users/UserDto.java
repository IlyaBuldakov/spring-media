package ru.kiryanovid.domain.entity.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class UserDto {
    /**
     * Идентификатор пользователя
     */
    @Getter private Integer id;

    /**
     * Имя пользователя
     */
    @Getter private String name;

    /**
     * Электронная почта пользователя
     */
    @Getter private String email;

    /**
     * Изображение пользователя
     */
    @Getter private byte[] avatar;

    /**
     * Роль пользователя
     */
    @Getter private RoleDto role;
}
