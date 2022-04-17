package domain.entities.comments;

import domain.entities.user.UserDto;

import java.time.LocalDateTime;

public class CommentDto {
    private int id;
    private LocalDateTime date;
    private UserDto user;
    private String message;
}
