package com.htc.application.dto.comment;

import com.htc.application.dto.user.UserShortResponse;
import com.htc.domain.entities.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CommentResponse {

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.dateCreated = comment.getDateCreated();
        this.author = new UserShortResponse(comment.getAuthor());
        this.message = comment.getMessage();
    }

    public static List<CommentResponse> createFromEntityList(List<? extends Comment> list) {
        List<CommentResponse> resultList = new ArrayList<>();
        for (Comment comment : list) {
            resultList.add(new CommentResponse(comment));
        }
        return resultList;
    }

    private @Getter int id;

    private @Getter LocalDate dateCreated;

    private @Getter UserShortResponse author;

    private @Getter String message;
}
