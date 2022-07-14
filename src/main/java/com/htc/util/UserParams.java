package com.htc.util;

import com.htc.application.dto.user.UserRequest;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import lombok.Getter;

/**
 * Класс-контейнер для данных пользователя.
 */
public class UserParams {

    public UserParams(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.password = userRequest.getPassword();
        this.email = userRequest.getEmail();
        this.avatar = userRequest.getAvatar();
        this.role = userRequest.getRole();
    }

    public UserParams(User user) {
        this.id = String.valueOf(user.getId());
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.role = user.getRole();
    }

    public UserParams(String id, UserRequest userRequest) {
        this(userRequest);
        this.id = id;
    }

    /**
     * Идентификатор пользователя.
     *
     * @return Идентификатор пользователя.
     */
    private @Getter String id;

    /**
     * Имя пользователя.
     *
     * @return Имя пользователя.
     */
    private final @Getter String name;

    /**
     * Пароль пользователя.
     *
     * @return Пароль пользователя.
     */
    private final @Getter String password;


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
     * Роль пользователя.
     *
     * @return Роль пользователя.
     */
    private final @Getter Role role;
}
