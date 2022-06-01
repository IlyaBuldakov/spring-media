package ru.kiryanovid.application.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kiryanovid.domain.entity.users.Role;

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
