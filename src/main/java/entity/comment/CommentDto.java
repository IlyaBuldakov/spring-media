package entity.comment;

import entity.users.UserBasicDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
public class CommentDto {
    /**
     *
     */
    @Getter private Integer id;

    /**
     *
     */
    @Getter private LocalDateTime date;

    /**
     *
     */
    @Getter private UserBasicDto user;

    /**
     *
     */
    @Getter private String message;
}
