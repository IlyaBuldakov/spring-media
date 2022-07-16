package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import lombok.Getter;

/**
 * Представление сущности роли (ответ).
 */
public class RoleResponse {

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    /**
     * Идентификатор роли.
     *
     * @return Идентификатор роли.
     */
    private final @Getter int id;

    private final @Getter String name;
}
