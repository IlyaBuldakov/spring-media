package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import lombok.Getter;

/**
 * Представление сущности роли (ответ).
 */
public class RoleResponse {

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.role = role;
    }

    /**
     * Идентификатор роли.
     */
    private final @Getter int id;

    /**
     * Имя роли.
     */
    private final @Getter Role role;
}
