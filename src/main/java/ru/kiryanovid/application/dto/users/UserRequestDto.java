package ru.kiryanovid.application.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.kiryanovid.domain.entity.users.Role;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    /**
     * Имя пользователя
     */
    @Getter private String name;

    /**
     * Электронная почта пользователя
     */
    @Getter private String email;

    /**
     * Пароль пользователя
     */
    @Getter private String password;

    /**
     * Изображение пользователя
     */
    @Getter private String avatar;

    /**
     * Роль пользователя
     */
    @Getter private Role role;
}
