package domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Пользователь - основные данные
 */

@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDto {
    /**
     * Идентификатор пользователя.
     *
     * @return id Идентификатор пользователя.
     */
    private @Getter int id;

    /**
     * @return name Имя пользователя.
     */
    private @Getter String name;


}
