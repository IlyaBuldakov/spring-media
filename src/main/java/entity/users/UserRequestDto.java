package entity.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
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
