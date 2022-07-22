package com.htc.application.dto.comment;

import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Представление комментария (ответ).
 */
@AllArgsConstructor
public class CommentResponse {

    /**
     * Конструктор из соответствующей сущности.
     *
     * @param comment Сущность {@link Comment комментария}.
     */
    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.dateCreated = comment.getDateCreated();
        this.author = new UserShortResponse(comment.getAuthor());
        this.message = comment.getMessage();
    }

    /**
     * Создание списка представления комментариев
     * из списка соответствующих сущностей.
     *
     * @param list Список сущностей {@link Comment}.
     * @return Список представлений (DTO) {@link CommentResponse}.
     */
    public static List<CommentResponse> createFromEntityList(List<? extends Comment> list) {
        List<CommentResponse> resultList = new ArrayList<>();
        for (Comment comment : list) {
            resultList.add(new CommentResponse(comment));
        }
        return resultList;
    }

    /**
     * Идентификатор комментария.
     */
    private @Getter int id;

    /**
     * Дата создания комментария.
     */
    private @Getter LocalDate dateCreated;

    /**
     * Автор комментария.
     */
    private @Getter UserShortResponse author;

    /**
     * Сообщение (содержимое) комментария.
     */
    private @Getter String message;
}
