package ru.kiryanovid.domain.entity.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public class RoleDto {
    /**
     * Идентификатор пользователя
     */
    @Getter private Integer id;

    /**
     * Роль пользователя
     */
    @Getter private Role role;
}
