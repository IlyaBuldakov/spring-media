package application.dto.user;

import application.dto.DataTransferObject;
import domain.entities.user.User;
import lombok.Getter;

/**
 * Краткое представление сущности пользователя (ответ)
 *
 * @author IlyaBuldakov
 */
public class UserShortResponse extends DataTransferObject<User> {

    public UserShortResponse(User user) {
        super(user);

        this.id = user.getId();
        this.name = user.getName();
    }

    /**
     * Идентификатор пользователя
     *
     * @return id Идентификатор пользователя
     */
    private final @Getter int id;

    /**
     * Имя пользователя
     *
     * @return name Имя пользователя
     */
    private final @Getter String name;
}
