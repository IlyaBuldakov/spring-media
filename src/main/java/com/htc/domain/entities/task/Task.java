package com.htc.domain.entities.task;

import com.htc.domain.entities.comment.Comment;
import com.htc.domain.entities.content.Content;
import com.htc.domain.entities.content.ContentType;
import com.htc.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * Класс, описывающий задачу.
 */
@AllArgsConstructor
public class Task {

    /**
     * Идентификатор задачи.
     *
     * @return Идентификатор задачи.
     */
    private @Getter int id;

    /**
     * Название задачи.
     *
     * @return Название задачи.
     */
    private @Getter String name;

    /**
     * Тип задачи.
     *
     * @return Тип задачи.
     */
    private @Getter ContentType type;

    /**
     * Описание задачи.
     *
     * @return Описание задачи.
     */
    private @Getter String description;

    /**
     * Приложенные файлы.
     *
     * @return Приложенные файлы.
     */
    private @Getter File[] files;

    /**
     * Автор задачи.
     *
     * @return Автор задачи.
     */
    private @Getter User author;

    /**
     * Исполнитель задачи.
     *
     * @return Исполнитель задачи.
     */
    private @Getter User executor;

    /**
     * Дата создания.
     *
     * @return Дата создания.
     */
    private @Getter LocalDate dateCreated;

    /**
     * Дата выполнения.
     *
     * @return Дата выполнения.
     */
    private @Getter LocalDate dateExpired;

    /**
     * Приложенный контент.
     *
     * @return Список с контентом.
     */
    private @Getter List<Content> contents;

    /**
     * Комментарии.
     *
     * @return Список комментариев.
     */
    private @Getter List<Comment> comments;

    /**
     * Статус задачи {@link TaskStatus.Status}.
     *
     * @return Статус задачи.
     */
    private @Getter TaskStatus status;
}
