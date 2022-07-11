package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Представление сущности пользователя (запрос).
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
     * Имя пользователя.
     *
     * @return Имя пользователя.
     */
    private @Getter String name;

    /**
     * Пароль пользователя.
     *
     * {@link User#getPassword() Требования к паролю}.
     *
     * @return Пароль пользователя.
     */
    private @Getter String password;

    /**
     * E-mail пользователя.
     *
     * @return E-mail пользователя.
     */
    private @Getter String email;

    /**
     * Аватар пользователя.
     *
     * @return Аватар пользователя.
     */
    private @Getter String avatar;

    /**
     * Роль пользователя {@link Role.RoleType}.
     *
     * @return Роль пользователя.
     */
    private @Getter Role role;
}
