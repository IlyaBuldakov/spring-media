package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Представление сущности пользователя (ответ).
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
     * Идентификатор пользователя.
     *
     * @return Идентификатор пользователя.
     */
    private final @Getter int id;

    /**
     * Имя пользователя.
     *
     * @return Имя пользователя.
     */
    private final @Getter String name;

    /**
     * E-mail пользователя.
     *
     * @return E-mail пользователя.
     */
    private final @Getter String email;

    /**
     * Аватар пользователя.
     *
     * @return Аватар пользователя.
     */
    private final @Getter String avatar;

    /**
     * Роль пользователя {@link Role.RoleType}.
     *
     * @return Роль пользователя.
     */
    private final @Getter Role role;
}
