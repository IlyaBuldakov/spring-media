package com.htc.application.dto.user;

import com.htc.application.dto.DataTransferObject;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.Role.RoleType;
import lombok.Getter;

/**
 * @author IlyaBuldakov
 */
public class RoleResponse extends DataTransferObject<Role> {

    public RoleResponse(Role role) {
        super(role);

        this.id = role.getId();
        this.roleType = role.getRoleType();
    }

    /**
     * Идентификатор роли.
     *
     * @return id Идентификатор роли.
     */
    private final @Getter int id;

    /**
     * Тип роли {@link RoleType}
     *
     * @return roleType Тип роли
     */
    private final @Getter RoleType roleType;
}