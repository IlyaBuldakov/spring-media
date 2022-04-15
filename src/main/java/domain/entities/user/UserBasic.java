package domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Упрощённое представление пользователя
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class UserBasic {

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
}
