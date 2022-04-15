package domain.entities.comment;

import domain.entities.user.UserBasic;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author IlyaBuldakov
 */

@AllArgsConstructor
public class CommentRequest {

    /**
     * Автор
     *
     * @return Автор
     */
    private @Getter UserBasic user;

    /**
     * Идентификатор задачи
     *
     * @return Идентификатор задачи
     */
    private @Getter int task;

    /**
     * Текст комментария
     *
     * @return message Текст комментария
     */
    private @Getter String message;
}
