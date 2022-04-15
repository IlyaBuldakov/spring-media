package domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс, описывающий роль пользователя {@link RoleType}
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class UserRole {

    /**
     * Тип роли
     */
    public enum RoleType {
        /**
         * Администратор
         */
        ADMIN,

        /**
         * Менеджер
         */
        MANAGER,

        /**
         * Контент-мейкер
         */
        CONTENT_MAKER
    }

    /**
     * Идентификатор роли.
     *
     * @return id Идентификатор роли.
     */
    private @Getter int id;

    /**
     * Тип роли {@link RoleType}
     *
     * @return roleType Тип роли
     */
    private @Getter RoleType roleType;

}
