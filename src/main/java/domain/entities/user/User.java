package domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс, описывающий пользователя
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class User {

    /**
     * Идентификатор пользователя
     *
     * @return id Идентификатор пользователя
     */
    private @Getter int id;

    /**
     * Имя пользователя
     *
     * @return name Имя пользователя
     */
    private @Getter String name;

    /**
     * Пароль пользователя
     *
     * @return password Пароль пользователя
     */
    private @Getter String password;

    /**
     * E-mail пользователя
     *
     * @return email E-mail пользователя
     */
    private @Getter String email;

    /**
     * Аватар пользователя
     *
     * @return avatar Аватар пользователя
     */
    private @Getter byte[] avatar;

    /**
     * Роль пользователя {@link UserRole.RoleType}
     *
     * @return role Роль пользователя
     */
    private @Getter UserRole role;
}
