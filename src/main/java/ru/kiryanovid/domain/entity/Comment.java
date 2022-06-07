package ru.kiryanovid.domain.entity;

import lombok.Getter;
import ru.kiryanovid.application.dto.users.UserBasicDto;

import java.time.LocalDateTime;

public class Comment {
    /**
     *
     */
    @Getter
    private Integer id;

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
