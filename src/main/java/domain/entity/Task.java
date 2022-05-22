package domain.entity;

import domain.entity.Comment;
import domain.entity.Content;
import domain.entity.User;
import domain.entity.task.ContentType;
import domain.entity.task.Status;
import lombok.Getter;

import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Задача
 */
public class Task {
    /**
     *
     */
    @Getter private Integer id;

    /**
     *
     */
    @Getter private String name;

    /**
     *
     */
    @Getter private ContentType contentType;

    /**
     *
     */
    @Getter private String description;

    /**
     *
     */
    @Getter private Path file;

    /**
     *
     */
    @Getter private User author;

    /**
     *
     */
    @Getter LocalDateTime dateCreate;

    /**
     *
     */
    @Getter LocalDateTime dateExpired;

    /**
     *
     */
    @Getter Content contents;

    /**
     *
     */
    @Getter Comment comments;

    /**
     *
     */
    @Getter
    Status status;

}
