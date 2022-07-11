package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.Role.RoleType;
import lombok.Getter;

/**
 * Представление сущности роли (ответ).
 */
public class RoleResponse {

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.roleType = role.getRoleType();
    }

    /**
     * Идентификатор роли.
     *
     * @return Идентификатор роли.
     */
    private final @Getter int id;

    /**
     * Тип роли {@link RoleType}.
     *
     * @return Тип роли.
     */
    private final @Getter RoleType roleType;
}
