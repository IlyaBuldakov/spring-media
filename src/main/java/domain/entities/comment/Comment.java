package domain.entities.comment;

import domain.entities.user.UserBasic;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Класс, описывающий сущность комментария
 *
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class Comment {

    /**
     * Идентификатор комментария
     *
     * @return id Идентификатор комментария
     */
    private @Getter int id;

    /**
     * Дата создания комментария
     *
     * @return date Дата создания
     */
    private @Getter LocalDate date;

    /**
     * Автор комментария
     *
     * @return user Автор комментария
     */
    private @Getter UserBasic user;

    /**
     * Сообщение (содержимое) комментария
     *
     * @return message Сообщение комментария
     */
    private @Getter String message;
}
