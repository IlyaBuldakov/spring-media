package domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запрос данных пользователя
 */
@AllArgsConstructor
public class UserRequestDto extends UserBasicDto {

    /**
     * Электронная почта пользователя.
     *
     * @return id Электронная почта пользователя.
     */
    private @Getter String email;

    /**
     * Изображение пользователя.
     *
     * @return id Изображение пользователя.
     */
    private @Getter byte[] avatar;

    /**
     * Роль пользователя, см. {@link RoleDto}.
     *
     * @return id Роль пользователя.
     */
    private @Getter RoleDto role;

    /**
     * @return password Пароль пользователя
     */
    private @Getter String password;
}
