package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Представление сущности пользователя (ответ)
 *
 * @author IlyaBuldakov
 */
@NoArgsConstructor
public class UserRequest {

    public UserRequest(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.role = user.getRole();
    }

    /**
     * Имя пользователя
     *
     * @return name Имя пользователя
     */
    private final @Getter String name;

    /**
     * Пароль пользователя
     *
     * {@link User#getPassword() Требования к паролю}
     *
     * @return password Пароль пользователя
     */
    private final @Getter String password;

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
    private final @Getter byte[] avatar;

    /**
     * Роль пользователя {@link Role.RoleType}
     *
     * @return role Роль пользователя
     */
    private final @Getter Role role;
}
