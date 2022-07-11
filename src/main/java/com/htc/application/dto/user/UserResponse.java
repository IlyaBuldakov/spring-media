package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Представление сущности пользователя (ответ)
 *
 * @author IlyaBuldakov
 */
public class UserResponse {

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.role = user.getRole();
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

    /**
     * E-mail пользователя
     *
     * @return email E-mail пользователя
     */
    private final @Getter String email;

    /**
     * Аватар пользователя
     *
     * @return avatar Аватар пользователя
     */
    private final @Getter String avatar;

    /**
     * Роль пользователя {@link Role.RoleType}
     *
     * @return role Роль пользователя
     */
    private final @Getter Role role;
}
