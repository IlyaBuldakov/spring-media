package com.htc.application.dto.user;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Представление сущности пользователя (запрос).
 */
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    /**
     * Имя пользователя.
     */
    private @Getter String name;

    /**
     * Пароль пользователя.
     *
     * {@link User#getPassword() Требования к паролю}.
     */
    private @Getter String password;

    /**
     * E-mail пользователя.
     */
    private @Getter String email;

    /**
     * Аватар пользователя.
     */
    private @Getter String avatar;

    /**
     * Роль пользователя.
     */
    private @Getter Role role;
}
